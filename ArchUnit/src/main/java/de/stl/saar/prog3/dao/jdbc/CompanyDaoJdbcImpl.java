package de.stl.saar.prog3.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.utils.JdbcUtils;

public final class CompanyDaoJdbcImpl implements CompanyDao {
	private static final String COL_COMPANY_ID = "companyId";
	private static final String COL_COMPANY_NAME = "companyName";
	private static final String COL_EMPLOYEES_COUNT = "employeeCount";
	private static final String COL_COUNT = "companyCount";
	
	@Override
	public Company deleteCompany(final long companyId) {
		Connection connection = null;
		try {
			final Company deletedCompany = findCompanyByPrimaryKey(companyId);
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "DELETE from Company WHERE "
									+ "companyId = " + companyId;
			statement.executeUpdate(queryString);
			return deletedCompany;
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
	public Company findCompanyByPrimaryKey(final long companyId) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final String queryString = "SELECT * from Company WHERE "
									 + "companyId = ?";
			final PreparedStatement preparedStatement = connection.
					prepareStatement(queryString);
			preparedStatement.setLong(1, companyId);
			final ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			final Company company = createCompanyFromResultSet(resultSet);
			return company;
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
	
	@Override
	public Company findCompanyByCompanyName(final String companyName) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "SELECT * from Company WHERE "
									 + "companyName = '" + companyName 
									 + "'";
			final ResultSet resultSet = statement.executeQuery(queryString);
			resultSet.next();
			final Company company = createCompanyFromResultSet(resultSet);
			return company;
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
	
	private Company createCompanyFromResultSet(final ResultSet resultSet) throws SQLException {
		final long companyId = resultSet.getLong(COL_COMPANY_ID);
		final String companyName = resultSet.getString(COL_COMPANY_NAME);
		/* Aus Demonstrationszwecken eine Ausgabe, falls das Feld companyName
		 * einen NULL-Wert enthaelt. Vor Aufruf von wasNull() muss auf
		 * dem entsprechenden Feld eine getXXX-Methode aufgerufen werden.
		 */
		if (resultSet.wasNull()) {
			System.out.println("Bei Firma mit der Id " + companyId + 
					" ist kein Name angegeben!");
		}

		final int employeesCount = resultSet.getInt(COL_EMPLOYEES_COUNT);
		final Company company = ModelFactory.createCompany(companyId, companyName, employeesCount);
		return company;
	}

	@Override
	public List<Company> findAllCompanies() {
		Connection connection = null;
		final List<Company> companyList = new ArrayList<>();
		try {
			connection = JdbcUtils.getConnection();
			final Statement statement = connection.
					createStatement();
			final String queryString = "SELECT * FROM Company";
			final ResultSet resultSet = statement.
					executeQuery(queryString);				
			
			while (resultSet.next()) {
				final Company company = createCompanyFromResultSet(resultSet);
				companyList.add(company);
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
		return companyList;
	}

	@Override
	public Company insertCompany(final Company company) {
		Connection connection = null;
		try {
			company.setCompanyId(countCompanies() + 1);
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "INSERT INTO Company values (" 
								    + company.getCompanyId() + ", '" 
								    + company.getCompanyName() + "', "
									+ company.getEmployeesCount() + ")";
			statement.executeUpdate(queryString);	
			return company;
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
	public int countCompanies() {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			final Statement statement = connection.createStatement();
			final String queryString = "SELECT COUNT(*) as "
					+ COL_COUNT	+ " FROM Company";
			final ResultSet resultSet = statement.executeQuery(queryString);
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
	public Company updateCompany(final Company updatedCompany) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();			
			final Statement statement = connection.createStatement();			
			final String queryString = "UPDATE Company SET "  
					+ COL_COMPANY_NAME + " = '" + updatedCompany.getCompanyName() + "', "
					+ COL_EMPLOYEES_COUNT + " = " + updatedCompany.getEmployeesCount()  
					+ " WHERE companyId = " + updatedCompany.getCompanyId(); 
			statement.executeUpdate(queryString);
			return updatedCompany;
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
