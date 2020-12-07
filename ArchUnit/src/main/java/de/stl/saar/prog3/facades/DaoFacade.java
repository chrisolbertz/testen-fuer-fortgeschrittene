package de.stl.saar.prog3.facades;

import java.util.List;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.dao.interfaces.PersonDao;
import de.stl.saar.prog3.factories.DaoFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public class DaoFacade {
	private PersonDao personDao;
	private EquipmentDao equipmentDao;
	private CompanyDao companyDao;
	private static DaoFacade daoFacade;
	
	private DaoFacade() {
		personDao = DaoFactory.createPersonDao();
		equipmentDao = DaoFactory.createEquipmentDao();
		companyDao = DaoFactory.createCompanyDao();
	}
	
	public static DaoFacade getInstance() {
		if (daoFacade == null) {
			daoFacade = new DaoFacade();
		}
		
		return daoFacade;
	}
	
	public Person insertPerson(final Person person) {
		return personDao.insertPerson(person);
	}

	public int countPersons() {
		return personDao.countPersons();
	}

	public void saveEquipmentOfPerson(final Person person) {
		personDao.saveEquipmentOfPerson(person);
	}

	public Person updatePerson(final Person person) {
		return personDao.updatePerson(person);
	}

	public Person findPersonByPrimaryKey(final long personId) {
		return personDao.findPersonByPrimaryKey(personId);
	}

	public Person deletePerson(final long personId) {
		return personDao.deletePerson(personId);
	}

	public List<Person> findAllPersons() {
		return personDao.findAllPersons();
	}
	
	public Equipment deleteEquipment(final int equipmentId) {
		return equipmentDao.deleteEquipment(equipmentId);
	}

	public Equipment findEquipmentByPrimaryKey(final long equipmentId) {
		return equipmentDao.findEquipmentByPrimaryKey(equipmentId);
	}

	public List<Equipment> findAllEquipments() {
		return equipmentDao.findAllEquipment();
	}

	public Equipment insertEquipment(final Equipment equipment) {
		return equipmentDao.insertEquipment(equipment);
	}

	public int countEquipment() {
		return equipmentDao.countEquipments();
	}

	public Equipment updateEquipment(final Equipment updatedEquipment) {
		return equipmentDao.updateEquipment(updatedEquipment);
	}
	
	public Company deleteCompany(final int companyId) {
		return companyDao.deleteCompany(companyId);
	}

	public Company findCompanyByPrimaryKey(final long companyId) {
		return companyDao.findCompanyByPrimaryKey(companyId);
	}

	public List<Company> findAllCompanies() {
		return companyDao.findAllCompanies();
	}

	public Company insertCompany(final Company company) {
		return companyDao.insertCompany(company);
	}

	public int countCompanies() {
		return companyDao.countCompanies();
	}
}

