package de.stl.saar.prog3.dao.withmaps.database;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.prog3.model.interfaces.Person;

public class PersonTable {
	private Map<Long, Person> personTable;
	
	public PersonTable() {
		personTable = new HashMap<>();
	}
	
	public void createPerson(Person person) {
		long personCount = personTable.values().size();
		final Long personId = ++personCount;
		person.setPersonId(personId);
		personTable.put(personId, person);
	}
	
	public void insertPerson(final Person person) {
		if (person.getPersonId() == 0) {
			createPerson(person);
		} else {
			personTable.replace(person.getPersonId(), person);
		}
	}

	public Person selectFromPerson(long personId) {
		return personTable.get(personId);
	}

	public void deleteFromPerson(long personId) {
		personTable.remove(personId);
	}

	public List<Person> selectAllFromPerson() {
		final Collection<Person> personCollection = personTable.values();
		final Person[] personArray = new Person[personCollection.size()];
		personCollection.toArray(personArray);
		return Arrays.asList(personArray);
	}
	
	/*public Person selectPersonByPersonName(final String personName) {
		final List<Person> persons = selectAllFromPerson();
		
		for(final Person person: persons) {
			if (StringUtils.areStringsEqual(person.getPersonName(), personName)) {
				return person;
			}
		}
		
		return null;
	}*/

	public int selectCountFromPerson() {
		return personTable.size();
	}
	
	public Person updatePerson(final Person updatedPerson) {
		final long personId = updatedPerson.getPersonId(); 
		personTable.put(personId, updatedPerson);
		return personTable.get(personId);
	}
	
	public void saveEquipmentOfPerson(final Person person) {
		final Person personInDatabase = personTable.get(person.getPersonId());
		personInDatabase.setEquipment(person.getEquipment());
	}
}
