package de.stl.saar.prog3.service.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Person;

public interface PersonService {

	Person saveNewPerson(Person person);

	List<Person> findAllPersons();

	Person upatePerson(Person updatedPerson);

	Person deletePerson(Person person);

}
