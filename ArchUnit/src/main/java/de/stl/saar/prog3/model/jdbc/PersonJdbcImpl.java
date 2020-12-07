package de.stl.saar.prog3.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public class PersonJdbcImpl implements Person {
	private long personId;
	private String firstName;
	private String lastName;
	private Company company;
	private List<Equipment> equipment;
	
	public PersonJdbcImpl() {
		equipment = new ArrayList<>();
	}
	
	@Override
	public void addEquipment(final Equipment oneEquipment) {
		equipment.add(oneEquipment);
	}
	
	@Override
	public long getPersonId() {
		return personId;
	}
	
	@Override
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public Company getCompany() {
		return company;
	}
	
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public List<Equipment> getEquipment() {
		return equipment;
	}
	
	@Override
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
}
