package de.stl.saar.prog3.view.fx.model;

import java.util.List;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.utils.StringUtils;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonFx {
	private LongProperty personId;
	private StringProperty firstName;
	private StringProperty lastName;
	private Person person;
	
	public PersonFx() {
		firstName = new SimpleStringProperty(
				StringUtils.EMPTY_STRING);
		lastName = new SimpleStringProperty(
				StringUtils.EMPTY_STRING);
		personId = new SimpleLongProperty();
		this.person = ModelFactory.createPerson();
	}
	
	public PersonFx(final Person person) {
		firstName = new SimpleStringProperty(person.getFirstName());
		lastName = new SimpleStringProperty(person.getLastName());
		personId = new SimpleLongProperty(person.getPersonId());
		this.person = person;
	}
	
	public void setPersonId(final long personId) {
		this.personId.set(personId);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public Company getCompany() {
		return person.getCompany();
	}

	public long getPersonId() {
		return person.getPersonId();
	}
	
	public void setCompany(final Company company) {
		this.person.setCompany(company);
	}
	
	public void addEquipment(final Equipment equipment) {
		this.person.addEquipment(equipment);
	}
	
	public List<Equipment> getEquipment() {
		return person.getEquipment();
	}
	
	public Person getPerson() {
		return person;
	}
}
