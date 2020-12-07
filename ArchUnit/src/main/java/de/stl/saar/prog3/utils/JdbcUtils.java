package de.stl.saar.prog3.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import de.stl.saar.prog3.enums.DatabaseType;

/**
 * Diese Klasse enthaelt alle Verbindungsinformationen zur Datenbank. Sie muessen nur hier
 * Aenderungen vornehmen, wenn Sie das Beispiel auf Ihrem System ausfuehren moechten. Die
 * Konstanten werden in einer spaeteren Vorlesung noch durch eine bessere Moeglichkeit
 * ersetzt. 
 * @author christopher
 *
 */
public class JdbcUtils {
	public static final DatabaseType ACTUAL_DATABASE = 
			DatabaseType.HSQLDB;
	
	// ******* Verbindungsdaten fuer HSQLDB *******
	// URL fuer die Datenbank im RAM.
	//private static final String URL = "jdbc:hsqldb:mem:prog3db";
	// URL fuer die Speicherung in einer Datei.
	//private static final String URL = "jdbc:hsqldb:file:~/prog3db";
	//private static final String USER = "sa";
	//private static final String PASSWORD = "";
	// ******* Verbindungsdaten fuer MYSQL *******
	//private static final String URL = "jdbc:mysql://localhost:3306/prog3?serverTimezone=Europe/Berlin";
	private static final String USER = "christopher";
	private static final String PASSWORD = "";
	// URL fuer SQL-Injection.
	private static final String URL = "jdbc:mysql://localhost:3306/prog3?allowMultiQueries=true";
	
	static {
		JdbcDatabaseInitializationUtil.initializeDatabase();
	}
	
	private JdbcUtils() {
	}
	
	public static Connection getConnection() 
			throws SQLException {
		return DriverManager.getConnection(
				URL, USER, PASSWORD);
	}
	
	public static void printDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
				
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println(driver.getClass().getName());
		}
	}
}