package de.stl.saar.prog3.factories;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.dao.interfaces.PersonDao;
import de.stl.saar.prog3.dao.jdbc.CompanyDaoJdbcImpl;
import de.stl.saar.prog3.dao.jdbc.EquipmentDaoJdbcImpl;
import de.stl.saar.prog3.dao.jdbc.PersonDaoJdbcImpl;

public class DaoFactory {
	public static PersonDao createPersonDao() {
		return new PersonDaoJdbcImpl();
	}
	
	public static EquipmentDao createEquipmentDao() {
		return new EquipmentDaoJdbcImpl();
	}
	
	public static CompanyDao createCompanyDao() {
		return new CompanyDaoJdbcImpl();
	}
}
