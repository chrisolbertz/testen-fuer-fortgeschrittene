package de.stl.saar.prog3.model.interfaces;

import java.util.List;

public interface Person {
	long getPersonId();
	void setPersonId(long personId);
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
	Company getCompany();
	List<Equipment> getEquipment();
	void setEquipment(List<Equipment> equipment);
	void addEquipment(Equipment oneEquipment);
	void setCompany(Company company);
}
