package de.stl.saar.prog3.factories;

import de.stl.saar.prog3.model.classes.CompanyImpl;
import de.stl.saar.prog3.model.classes.EquipmentImpl;
import de.stl.saar.prog3.model.classes.PersonImpl;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public class ModelFactory {

	public static Person createPerson(long personId, String firstName, String lastName) {
		final Person person = new PersonImpl();
		person.setPersonId(personId);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}
	
	public static Person createPerson(String firstName, String lastName) {
		final Person person = new PersonImpl();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}
	
	public static Person createPerson() {
		final Person person = new PersonImpl();
		return person;
	}
	
	public static Company createCompany() {
		final Company company = new CompanyImpl();
		return company;
	}

	public static Company createCompany(long companyId, String companyName) {
		final Company company = new CompanyImpl();
		company.setCompanyId(companyId);
		company.setCompanyName(companyName);
		return company;
	}
	
	public static Company createCompany(long companyId, String companyName, int employeesCount) {
		final Company company = new CompanyImpl();
		company.setCompanyId(companyId);
		company.setCompanyName(companyName);
		company.setEmployeesCount(employeesCount);
		return company;
	}
	
	public static Company createCompany(String companyName, int employeesCount) {
		return createCompany(0, companyName, employeesCount);
	}
	
	public static Equipment createEquipment() {
		final Equipment equipment = new EquipmentImpl();
		return equipment;
	}
	
	public static Equipment createEquipment(long equipmentId, String equipmentName, double weight) {
		final Equipment equipment = new EquipmentImpl();
		equipment.setEquipmentId(equipmentId);
		equipment.setEquipmentName(equipmentName);
		equipment.setWeight(weight);
		
		return equipment;
	}
	
	public static Equipment createEquipment(String equipmentName, double weight) {
		return createEquipment(0, equipmentName, weight);
	}
}
