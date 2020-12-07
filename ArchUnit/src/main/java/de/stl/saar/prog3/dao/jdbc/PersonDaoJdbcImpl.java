package de.stl.saar.prog3.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.dao.interfaces.PersonDao;
import de.stl.saar.prog3.factories.DaoFactory;
import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.utils.JdbcUtils;

public final class PersonDaoJdbcImpl implements PersonDao {
	private static final String COL_FIRST_NAME = "firstName";
	private static final String COL_LAST_NAME = "lastName";
	private static final String COL_PERSON_COUNT = "personCount";
	private static final String COL_PERSON_ID = "personId";
	private static final String COL_COMPANY_ID_FK = "companyId_fk";
	
	private CompanyDao companyDao;
	private EquipmentDao equipmentDao;
	
	public PersonDaoJdbcImpl() {
		companyDao = DaoFactory.createCompanyDao();
		equipmentDao = DaoFactory.createEquipmentDao();
	}
	
	@Override
	public Person insertPerson(final Person person) {
		try {
			final int nextPersonId = countPersons() + 1;
			person.setPersonId(nextPersonId);
			final Connection connection = JdbcUtils.getConnection();
			final Statement addPersonStatement = connection.createStatement();
			final String addPersonQuery = "INSERT INTO person (personId, firstName, "
					+ "lastName, companyId_fk) "
					+ "VALUES (" + person.getPersonId() + ", '" + 
								  person.getFirstName() + "', '" + 
					              person.getLastName() + "'," +
								  person.getCompany().getCompanyId() + ");";
			addPersonStatement.executeUpdate(addPersonQuery);
			return person;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Person updatePerson(final Person person) {
		try {
			final long personId = person.getPersonId();
			final Connection connection = JdbcUtils.getConnection();
			final Statement updatePersonStatement = connection.createStatement();
			final String updatePersonQuery = "UPDATE person SET " +
					"lastName = '" + person.getLastName() + "'," +
					"firstName = '" + person.getFirstName() + "' " +
					"WHERE personId = " + personId;
			updatePersonStatement.executeUpdate(updatePersonQuery);
			return person;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Person deletePerson(final long personId) {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final Statement updatePersonStatement = connection.createStatement();
			final Person person = findPersonByPrimaryKey(personId);
			final String updatePersonQuery = "DELETE FROM person " +
					"WHERE personId = " + personId;
			updatePersonStatement.executeUpdate(updatePersonQuery);
			return person;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override 
	public Person findPersonByPrimaryKey(final long personId) {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final String findPersonQuery = "SELECT * FROM Person WHERE " + 
					"personId = " + personId;
			final Statement findPersonStatement = connection.createStatement();
			final ResultSet resultSet = findPersonStatement.executeQuery(findPersonQuery);
			Person person = null;
			
			while(resultSet.next()) {
				person = createPersonFromResultSet(resultSet);
			}
			
			return person;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override 
	public List<Person> findAllPersons() {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final String findPersonQuery = "SELECT * FROM person";
			final Statement findPersonStatement = connection.createStatement();
			final ResultSet resultSet = findPersonStatement.executeQuery(findPersonQuery);
			final List<Person> persons = new ArrayList<>();
			
			while(resultSet.next()) {
				final Person person = createPersonFromResultSet(resultSet);
				persons.add(person);
			}
			
			return persons;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	private Person createPersonFromResultSet(final ResultSet resultSet) throws SQLException {
		final long personId = resultSet.getLong(COL_PERSON_ID);
		final String firstName = resultSet.getString(COL_FIRST_NAME);
		final String lastName = resultSet.getString(COL_LAST_NAME);
		final long companyId = resultSet.getLong(COL_COMPANY_ID_FK);
		final Person person = ModelFactory.createPerson(personId, firstName, lastName);
		final Company company = companyDao.findCompanyByPrimaryKey(companyId);
		person.setCompany(company);
		final List<Equipment> equipment = equipmentDao.findEquipmentOfPerson(person);
		person.setEquipment(equipment);
		return person;
	}
	
	@Override
	public void saveEquipmentOfPerson(final Person person) {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final Statement saveEquipmentOfPersonStatement = connection.createStatement();
			final List<Equipment> equipmentOfPerson = person.getEquipment();
			
			if (equipmentOfPerson != null) {
				for (Equipment equipment: equipmentOfPerson) {
					final String saveEquipmentOfPersonQuery = "INSERT INTO personHasEquipment " 
							+ "VALUES (" + person.getPersonId() + ", " + 
										  equipment.getEquipmentId() + ")";
					saveEquipmentOfPersonStatement.executeUpdate(saveEquipmentOfPersonQuery);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override 
	public int countPersons() {
		try {
			final Connection connection = JdbcUtils.getConnection();
			final String countPersonsQuery = "SELECT COUNT(*) as " + 
					COL_PERSON_COUNT + " FROM Person;";
			final Statement countPersonsStatement = connection.createStatement();
			final ResultSet resultSet = countPersonsStatement.executeQuery(countPersonsQuery);
			resultSet.next();
			final int personCount = resultSet.getInt(COL_PERSON_COUNT);
			return personCount;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return 0;
	}
	
	public static void trySqlInjection(final String userName) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "SELECT * from person WHERE "
									 + "firstname = '" + userName + "'";
			final ResultSet resultSet = statement.executeQuery(queryString);
			while (resultSet.next()) {
				final String username = resultSet.getString(2);
				System.out.println(username);
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
	}
	
	public static void trySqlInjectionAgain(final String userName, final long userId) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			final String queryString = "UPDATE person set firstName = '" + 
					userName + "' WHERE personId = " + userId;
			final Statement statement = connection.createStatement();			
			
			statement.executeUpdate(queryString);						
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
	}
}
