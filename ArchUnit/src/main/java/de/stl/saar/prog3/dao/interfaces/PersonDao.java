package de.stl.saar.prog3.dao.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Person;

public interface PersonDao {

	Person insertPerson(Person person);

	int countPersons();

	void saveEquipmentOfPerson(Person person);

	Person updatePerson(Person person);

	Person findPersonByPrimaryKey(long personId);

	Person deletePerson(long personId);

	List<Person> findAllPersons();

}
