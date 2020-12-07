package de.stl.saar.prog3.service.implementation;

import java.util.List;

import de.stl.saar.prog3.facades.DaoFacade;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.service.interfaces.PersonService;

public class PersonServiceImpl implements PersonService {
	private DaoFacade daoFacade;
	
	public PersonServiceImpl() {
		daoFacade = DaoFacade.getInstance();
	}

	@Override
	public Person deletePerson(final Person person) {
		return daoFacade.deletePerson(person.getPersonId());
	}
	
	@Override
	public Person saveNewPerson(final Person person) {
		Person newPerson = daoFacade.insertPerson(person);
		daoFacade.saveEquipmentOfPerson(newPerson);
		return newPerson;
	}
	
	@Override
	public List<Person> findAllPersons() {
		return daoFacade.findAllPersons();
	}
	
	@Override
	public Person upatePerson(final Person updatedPerson) {
		return daoFacade.updatePerson(updatedPerson);
	}
}
