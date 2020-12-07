package de.stl.saar.prog3.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import de.stl.saar.prog3.constants.DatabaseConstants;
import de.stl.saar.prog3.dao.withmaps.database.CompanyTable;
import de.stl.saar.prog3.dao.withmaps.database.Database;
import de.stl.saar.prog3.dao.withmaps.database.EquipmentTable;
import de.stl.saar.prog3.dao.withmaps.database.PersonTable;
import de.stl.saar.prog3.enums.DatabaseType;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

/**
 * Initialisiert die Datenbank bei der Verwendung von JDBC mit Testdaten.
 * @author christopher
 *
 */
public class JdbcDatabaseInitializationUtil {
	private static PersonTable personTable;
	private static EquipmentTable equipmentTable;
	private static CompanyTable companyTable;
	
	private JdbcDatabaseInitializationUtil() {
	}
	
	/**
	 * Initialisiert die Datenbank mit Testdaten entweder fuer MySQL oder
	 * fuer HSQLDB. Die Information, welche Datenbank verwendet wird, wird
	 * aus {@see de.stl.saar.prog3.utils.JdbcUtils} genommen.
	 */
	public static void initializeDatabase() {
		final Database database = Database.getInstance();
		companyTable = database.getCompanyTable();
		equipmentTable = database.getEquipmentTable();
		personTable = database.getPersonTable();
		
		deleteAllTables();
		
		final DatabaseType databaseType = JdbcUtils.ACTUAL_DATABASE;
		if (databaseType.equals(DatabaseType.MYSQL)) {
			createAllTablesAndTestDataMySql();
		} else if (databaseType.equals(DatabaseType.HSQLDB)) {
			createAllTablesAndTestDataHSQLDB();
		}
	}
	
	/**
	 * Checks if a table exists in a MYSQL database.
	 * @param tableName The name of the table we want to check.
	 * @return True if the table not exists false otherwise. 
	 */
	private static boolean doesTableNotExistMySQL(final String tableName) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet res = meta.getTables(null, null, tableName, 
					new String[] {"TABLE"});
			if (res.next()) {
				return false;
			} else {
				return true;
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
		return false;
	}
	
	/**
	 * Erzeugt saemtliche Tabellen der Anwendung fuer MySQL und schreibt die Testdaten hinein.
	 */
	private static void createAllTablesAndTestDataMySql() {
		Connection connection = null;
		final List<String> createStatements = DatabaseConstants.getCreateStatementsForAllMySqlTables();
		
		try {
			connection = JdbcUtils.getConnection();
			
			for (final String aStatement: createStatements) {
				final Statement statement = connection.createStatement();
				final int posTableNameEnd = aStatement.indexOf("(");
				final String tableName = aStatement.substring(12, posTableNameEnd).trim();
				if (doesTableNotExistMySQL(tableName)) {
					statement.executeUpdate(aStatement);
				}
			}
			createTestdataForCompanies();
			createTestdataForEquipment();
			createTestdataForPersons();
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
	
	/**
	 * Erzeugt saemtliche Tabellen der Anwendung fuer HSQLDB und schreibt die Testdaten hinein.
	 */
	private static void createAllTablesAndTestDataHSQLDB() {
		Connection connection = null;
		final List<String> createStatements = DatabaseConstants.getCreateStatementsForAllHsqlDbTables();
		
		try {
			connection = JdbcUtils.getConnection();
			
			for (final String aStatement: createStatements) {
				final Statement statement = connection.createStatement();
				statement.executeUpdate(aStatement);
			}
			createTestdataForCompanies();
			createTestdataForEquipment();
			createTestdataForPersons();
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
	
	/**
	 * Loescht alle Tabellen mit dem Kommando DROP IF EXIST
	 */
	private static void deleteAllTables() {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			final Statement deleteStatementCompany = connection.createStatement();
			deleteStatementCompany.executeUpdate("DROP TABLE IF EXISTS company");
			
			final Statement deleteStatementEquipment = connection.createStatement();
			deleteStatementEquipment.executeUpdate("DROP TABLE IF EXISTS equipment");
			
			final Statement deleteStatementPerson = connection.createStatement();
			deleteStatementPerson.executeUpdate("DROP TABLE IF EXISTS person");
			
			final Statement deleteStatementPersonHasEquipment = connection.createStatement();
			deleteStatementPersonHasEquipment.executeUpdate("DROP TABLE IF EXISTS personhasequipment");
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
	
	/**
	 * Erzeugt die Testdaten fuer die Firmen.
	 */
	private static void createTestdataForCompanies() {
		Connection connection = null;
		Statement statement = null;
		
		try {
			// Einfuegen mittels Batch-Statement.
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			final List<Company> companies = companyTable.selectAllFromCompany();
			
			for (final Company company: companies) {
				String sqlStatement = "INSERT INTO company values ("
						+ company.getCompanyId() + ", '" 
						+ company.getCompanyName() + "', "
						+ company.getEmployeesCount() + ")";
				statement.addBatch(sqlStatement);
			}
			statement.executeBatch();
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
	
	/**
	 * Erzeugt die Testdaten fuer die Ausruestung.
	 */
	private static void createTestdataForEquipment() {
		Connection connection = null;
		Statement statement = null;
		
		try {
			// Einfuegen mittels Batch-Statement.
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			final List<Equipment> equipment = equipmentTable.selectAllFromEquipment();
			
			for (final Equipment oneEquipment: equipment) {
				String sqlStatement = "INSERT INTO equipment values (" 
						+ oneEquipment.getEquipmentId() + ", '" 
						+ oneEquipment.getEquipmentName() + "', " 
						+ oneEquipment.getWeight() + ")";
				statement.addBatch(sqlStatement);
			}
			statement.executeBatch();
			
			// Einfuegen mit Prepared Statement.			
			
			// Auskommentiert: Anweisungen für Transaktionen
			// Transaktionsverhalten einschalten.
			connection.setAutoCommit(false);
			//connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			/*System.out.println(connection.getTransactionIsolation());
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Article values (?, ?, ?)");
			for (Article article: articleTable) {
				preparedStatement.setInt(1, article.getArticleNr());
				preparedStatement.setString(2, article.getArticleName());
				preparedStatement.setDouble(3, article.getPrice());
				// Provozieren eines Fehlers, um Rollback-Verhalten zu testen.
				/*if (article.getArticleId() == 5) {
					preparedStatement.setString(4, "xx");
				}*/			
				/*preparedStatement.setInt(4, 1);				
				preparedStatement.executeUpdate();*/
			//}
			// Commit ausfuehren.
			//connection.commit();
			// Transaktionsverhalten zurueck auf Standard.
			//connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			// Rollback bei Fehler.
			//connection.rollback();
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
	
	/**
	 * Erzeugt die Testdaten fuer die Personen.
	 */
	private static void createTestdataForPersons() {
		Connection connection = null;
		Statement statement = null;
		
		try {
			// Einfuegen mittels Batch-Statement.
			connection = JdbcUtils.getConnection();
			statement = connection.createStatement();
			final List<Person> companies = personTable.selectAllFromPerson();
			
			for (final Person person: companies) {
				String sqlStatement = "INSERT INTO person values ("
						+ person.getPersonId() + ", '" 
						+ person.getFirstName() + "', '"
						+ person.getLastName() + "', "
						+ person.getCompany().getCompanyId() +")";
				
				statement.addBatch(sqlStatement);
				
				for (final Equipment equipment: person.getEquipment()) {
					String sqlEquipmentStatement = "INSERT INTO personHasEquipment values("
							+ person.getPersonId() + ", "
							+ equipment.getEquipmentId() + ");";
					statement.addBatch(sqlEquipmentStatement);
				}
			}
			
			statement.executeBatch();
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
