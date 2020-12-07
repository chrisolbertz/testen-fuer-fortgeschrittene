package de.stl.saar.prog3.constants;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConstants {
	public static final String COL_PERSON_ID = "personId";
	public static final String COL_FIRST_NAME = "firstName";
	public static final String COL_LAST_NAME = "lastName";
	public static final String COL_COMPANY_ID_FK = "companyId_fk";
	public static final String COL_COMPANY_ID = "companyId";
	public static final String COL_COMPANY_NAME = "companyName";
	public static final String COL_EMPLOYEE_COUNT = "employeeCount";
	public static final String COL_EQUIPMENT_ID = "equipmentId";
	public static final String COL_EQUIPMENT_NAME = "equipmentName";
	public static final String COL_WEIGHT = "weight";
	public static final String COL_EQUIPMENT_ID_FK = "equipmentId_fk";
	public static final String COL_PERSON_ID_FK = "personId_fk";
	
	public static String createSqlForCompanyMySqlTable() {
		return "CREATE TABLE company (" + COL_COMPANY_ID + " long, " +
								         COL_COMPANY_NAME + " varchar(100), " +
								         COL_EMPLOYEE_COUNT + " varchar(100));";
	}
	
	public static String createSqlForPersonMySqlTable() {
		return "CREATE TABLE person (" + COL_PERSON_ID + " long, " +
								         COL_FIRST_NAME + " varchar(100), " +
								         COL_LAST_NAME + " varchar(100), " +
								         COL_COMPANY_ID_FK + " long);";
	}
	
	public static String createSqlForEquipmentMySqlTable() {
		return "CREATE TABLE equipment (" + COL_EQUIPMENT_ID + " long, " +
								         COL_EQUIPMENT_NAME + " varchar(100), " +
								         COL_WEIGHT + " double);";
	}
	
	public static String createSqlForPersonHasEquipmentMySqlTable() {
		return "CREATE TABLE personHasEquipment (" + COL_PERSON_ID_FK + " long, " +
								         COL_EQUIPMENT_ID_FK + " long);";
	}
	
	public static List<String> getCreateStatementsForAllMySqlTables() {
		final List<String> statements = new ArrayList<String>();
		statements.add(createSqlForCompanyMySqlTable());
		statements.add(createSqlForPersonMySqlTable());
		statements.add(createSqlForEquipmentMySqlTable());
		statements.add(createSqlForPersonHasEquipmentMySqlTable());
		
		return statements;
	}

	public static String createSqlForCompanyHsqlDbTable() {
		return "CREATE TABLE company (" + COL_COMPANY_ID + " int, " +
								         COL_COMPANY_NAME + " varchar(100), " +
								         COL_EMPLOYEE_COUNT + " varchar(100));";
	}
	
	public static String createSqlForPersonHsqlDbTable() {
		return "CREATE TABLE person (" + COL_PERSON_ID + " int, " +
								         COL_FIRST_NAME + " varchar(100), " +
								         COL_LAST_NAME + " varchar(100), " +
								         COL_COMPANY_ID_FK + " int);";
	}
	
	public static String createSqlForEquipmentHsqlDbTable() {
		return "CREATE TABLE equipment (" + COL_EQUIPMENT_ID + " int, " +
								         COL_EQUIPMENT_NAME + " varchar(100), " +
								         COL_WEIGHT + " float);";
	}
	
	public static String createSqlForPersonHasEquipmentHsqlDbTable() {
		return "CREATE TABLE personHasEquipment (" + COL_PERSON_ID_FK + " int, " +
								         COL_EQUIPMENT_ID_FK + " int);";
	}
	
	public static List<String> getCreateStatementsForAllHsqlDbTables() {
		final List<String> statements = new ArrayList<String>();
		statements.add(createSqlForCompanyHsqlDbTable());
		statements.add(createSqlForPersonHsqlDbTable());
		statements.add(createSqlForEquipmentHsqlDbTable());
		statements.add(createSqlForPersonHasEquipmentHsqlDbTable());
		
		return statements;
	}
}
