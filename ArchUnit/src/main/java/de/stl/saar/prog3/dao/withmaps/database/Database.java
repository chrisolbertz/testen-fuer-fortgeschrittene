package de.stl.saar.prog3.dao.withmaps.database;

import java.util.List;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public class Database {
	private CompanyTable companyTable;
	private EquipmentTable equipmentTable;
	private PersonTable personTable;
	private static Database database;
	
	private Database() {
		personTable = new PersonTable();
		companyTable = new CompanyTable();
		equipmentTable = new EquipmentTable();
		initializeTestData();
	}
	
	private void initializeTestData() {
		initializeCompanies();
		initializeEquipment();
		initializePersons();
	}
	
	private void initializeCompanies() {
		final Company ctu = ModelFactory.createCompany(1, "CTU", 1500);
		final Company mi6 = ModelFactory.createCompany(2, "MI6", 1250);
		final Company starfleet = ModelFactory.createCompany(3, "Sternenflotte", 100000);
		companyTable.createCompany(ctu);
		companyTable.createCompany(mi6);
		companyTable.createCompany(starfleet);
	}
	
	private void initializeEquipment() {
		final Equipment waltherPKK = ModelFactory.createEquipment(1, "Walther PKK", 50);
		final Equipment enterprise = ModelFactory.createEquipment(2, "Enterprise", 1500000);
		final Equipment fastCarWithRocketLauncher = ModelFactory.createEquipment(3, "Schnelles Auto mit Raketenwerfer", 50000);
		final Equipment hacker = ModelFactory.createEquipment(4, "Hacker", 800);
		final Equipment cuffButtonWithHandGranates = ModelFactory.createEquipment(5, "Manschettenknopf mit Handgranaten", 500);
		final Equipment glassesWithLaserGun = ModelFactory.createEquipment(6, "Brille mit Laserkanone", 5000);
		
		equipmentTable.createEquipment(waltherPKK);
		equipmentTable.createEquipment(enterprise);
		equipmentTable.createEquipment(fastCarWithRocketLauncher);
		equipmentTable.createEquipment(hacker);
		equipmentTable.createEquipment(cuffButtonWithHandGranates);
		equipmentTable.createEquipment(glassesWithLaserGun);
	}
	
	private void initializePersons() {
		final Person jackBauer = ModelFactory.createPerson(1, "Jack", "Bauer");
		final Person jamesBond = ModelFactory.createPerson(2, "James", "Bond");
		final Person jeanlucPicard = ModelFactory.createPerson(3, "Jean-Luc", "Picard");
		
		final Equipment waltherPKK = equipmentTable.selectFromEquipment(1);
		final Equipment enterprise = equipmentTable.selectFromEquipment(2);
		final Equipment fastCarWithRocketLauncher = equipmentTable.selectFromEquipment(3);
		final Equipment hacker = equipmentTable.selectFromEquipment(4);
		final Equipment cuffButtonWithHandGranates = equipmentTable.selectFromEquipment(5);
		final Equipment glassesWithLaserGun = equipmentTable.selectFromEquipment(6);
		
		final Company ctu = companyTable.selectFromCompany(1);
		final Company mi6 = companyTable.selectFromCompany(2);
		final Company starfleet = companyTable.selectFromCompany(3);
		
		jackBauer.addEquipment(hacker);
		jamesBond.addEquipment(glassesWithLaserGun);
		jamesBond.addEquipment(cuffButtonWithHandGranates);
		jamesBond.addEquipment(fastCarWithRocketLauncher);
		jeanlucPicard.addEquipment(enterprise);
		jackBauer.addEquipment(waltherPKK);
		jamesBond.addEquipment(waltherPKK);
		
		jackBauer.setCompany(ctu);
		jamesBond.setCompany(mi6);
		jeanlucPicard.setCompany(starfleet);
		
		personTable.createPerson(jackBauer);
		personTable.createPerson(jamesBond);
		personTable.createPerson(jeanlucPicard);
	}
	
	public static Database getInstance() {
		if (database == null) {
			database = new Database();
		}
		
		return database;
	}
	
	public Person updatePerson(final Person updatedPerson) {
		return personTable.updatePerson(updatedPerson);
	}
	
	public void saveEquipmentOfPerson(final Person person) {
		personTable.saveEquipmentOfPerson(person);
	}
	
	public Person insertPerson(final Person person) {
		personTable.insertPerson(person);
		return personTable.selectFromPerson(person.getPersonId());
	}
	
	public Person selectFromPerson(final long personId) {
		return personTable.selectFromPerson(personId);
	}
	
	public Person deleteFromPerson(final long personId) {
		final Person person = selectFromPerson(personId);
		personTable.deleteFromPerson(personId);
		return person;
	}
	
	public List<Person> selectAllFromPerson() {
		return personTable.selectAllFromPerson();
	}
	
	public int selectCountFromPerson() {
		return personTable.selectCountFromPerson();
	}

	public Company deleteFromCompany(long companyId) {
		return companyTable.deleteFromCompany(companyId);
	}
	
	public Company selectFromCompany(long companyId) {
		return companyTable.selectFromCompany(companyId);
	}

	public List<Company> selectAllFromCompany() {
		return companyTable.selectAllFromCompany();
	}

	public Company insertCompany(Company company) {
		return companyTable.createCompany(company);
	}

	public int selectCountFromCompany() {
		return companyTable.selectCountFromCompany();
	}

	public Company updateCompany(Company updatedCompany) {
		return companyTable.saveCompany(updatedCompany);
	}
	
	public Equipment deleteFromEquipment(long equipmentId) {
		return equipmentTable.deleteFromEquipment(equipmentId);
	}
	
	public Equipment selectFromEquipment(long equipmentId) {
		return equipmentTable.selectFromEquipment(equipmentId);
	}

	public List<Equipment> selectAllFromEquipment() {
		return equipmentTable.selectAllFromEquipment();
	}

	public Equipment insertEquipment(Equipment equipment) {
		return equipmentTable.createEquipment(equipment);
	}

	public int selectCountFromEquipment() {
		return equipmentTable.selectCountFromEquipment();
	}

	public Equipment updateEquipment(Equipment updatedEquipment) {
		return equipmentTable.createEquipment(updatedEquipment);
	}
	
	public CompanyTable getCompanyTable() {
		return companyTable;
	}
	
	public EquipmentTable getEquipmentTable() {
		return equipmentTable;
	}
	
	public PersonTable getPersonTable() {
		return personTable;
	}
}
