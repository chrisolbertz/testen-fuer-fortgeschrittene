package de.stl.saar.prog3.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.utils.JdbcUtils;

public final class EquipmentDaoJdbcImpl implements EquipmentDao {

	private static final String COL_EQUIPMENT_ID = "equipmentId";
	private static final String COL_EQUIPMENT_NAME = "equipmentName";
	private static final String COL_WEIGHT = "weight";
	private static final String COL_COUNT = "equipmentCount";
	private static final String COL_EQUIPMENT_ID_fk = "equipmentId_fk";
	
	@Override
	public Equipment deleteEquipment(final long equipmentId) {
		Connection connection = null;
		try {
			final Equipment deletedEquipment = findEquipmentByPrimaryKey(equipmentId);
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "DELETE from Equipment WHERE "
									+ "equipmentId = " + equipmentId;
			statement.executeUpdate(queryString);
			return deletedEquipment;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public Equipment findEquipmentByPrimaryKey(final long equipmentId) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "SELECT * from Equipment WHERE "
									 + "equipmentId = " + equipmentId;
			final ResultSet resultSet = statement.executeQuery(queryString);
			resultSet.next();
			final Equipment equipment = createEquipmentFromResultSet(resultSet);
			return equipment;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	private Equipment createEquipmentFromResultSet(final ResultSet resultSet) throws SQLException {
		final long equipmentId = resultSet.getLong(COL_EQUIPMENT_ID);
		final String equipmentName = resultSet.getString(COL_EQUIPMENT_NAME);

		final double weight = resultSet.getInt(COL_WEIGHT);
		final Equipment equipment = ModelFactory.createEquipment(equipmentId, equipmentName, weight);
		return equipment;
	}
	
	@Override
	public List<Equipment> findEquipmentOfPerson(final Person person) {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final String findEquipmentQuery = "SELECT * FROM personhasequipment where personId_fk = ?";
			final PreparedStatement findPersonStatement = connection.prepareStatement(findEquipmentQuery);
			findPersonStatement.setLong(1, person.getPersonId());
			final ResultSet resultSet = findPersonStatement.executeQuery();
			final List<Equipment> equipmentList = new ArrayList<>();
			
			while(resultSet.next()) {
				final long equipmentId = resultSet.getLong(COL_EQUIPMENT_ID_fk);
				final Equipment equipment = findEquipmentByPrimaryKey(equipmentId);
				equipmentList.add(equipment);
			}
			
			return equipmentList;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Equipment> findAllEquipment() {
		Connection connection = null;
		final List<Equipment> equipmentList = new ArrayList<>();
		try {
			connection = JdbcUtils.getConnection();
			final Statement statement = connection.createStatement();
			final String queryString = "SELECT * FROM Equipment";
			final ResultSet resultSet = statement.executeQuery(queryString);				
			
			while (resultSet.next()) {
				final Equipment equipment = createEquipmentFromResultSet(resultSet);
				equipmentList.add(equipment);
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return equipmentList;
	}

	@Override
	public Equipment insertEquipment(final Equipment equipment) {
		Connection connection = null;
		try {
			equipment.setEquipmentId(countEquipments() + 1);
			connection = JdbcUtils.getConnection();
			final String queryString = "INSERT INTO Equipment values (?,?,?)";
			final PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setLong(1, equipment.getEquipmentId());
			preparedStatement.setString(2, equipment.getEquipmentName());
			preparedStatement.setDouble(3, equipment.getWeight());
			preparedStatement.executeUpdate();
			
			/*final Statement statement = connection.createStatement();			
			final String queryString = "INSERT INTO Equipment values (" 
								    + equipment.getEquipmentId() + ", '" 
									+ equipment.getEquipmentName() + "', "
									+ equipment.getWeight() + ")";
			statement.executeUpdate(queryString);*/
			return equipment;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return null;
	}

	@Override
	public int countEquipments() {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			Statement statement = connection.createStatement();
			String queryString = "SELECT COUNT(*) as " + COL_COUNT 
					+ " FROM Equipment";
			ResultSet resultSet = statement.executeQuery(queryString);
			resultSet.next();
			return resultSet.getInt(COL_COUNT);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public Equipment updateEquipment(Equipment updatedEquipment) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "UPDATE Equipment SET "  
					+ COL_EQUIPMENT_NAME + " = '" + updatedEquipment.getEquipmentName() + "', "
					+ COL_WEIGHT + " = " + updatedEquipment.getWeight()  
					+ " WHERE equipmentId = " + updatedEquipment.getEquipmentId(); 
			statement.executeUpdate(queryString);
			return updatedEquipment;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();					
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}
}
