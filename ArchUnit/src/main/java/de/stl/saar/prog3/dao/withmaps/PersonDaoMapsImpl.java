package de.stl.saar.prog3.dao.withmaps;

import java.util.List;

import de.stl.saar.prog3.dao.interfaces.PersonDao;
import de.stl.saar.prog3.dao.withmaps.database.Database;
import de.stl.saar.prog3.model.interfaces.Person;

public class PersonDaoMapsImpl implements PersonDao {
	private Database database;
	
	public PersonDaoMapsImpl() {
		database = Database.getInstance();
	}
	
	@Override 
	public Person deletePerson(final long personId){
		return database.deleteFromPerson(personId);
	}

	@Override
	public Person findPersonByPrimaryKey(final long personId) {
		return database.selectFromPerson(personId);
	}
	
	@Override
	public List<Person> findAllPersons() {
		return database.selectAllFromPerson();
	}
	
	@Override
	public Person insertPerson(final Person person) {
		return database.insertPerson(person);
	}
	
	@Override
	public int countPersons() {
		return database.selectCountFromPerson();
	}

	@Override
	public void saveEquipmentOfPerson(Person person) {
		database.saveEquipmentOfPerson(person);
	}

	@Override
	public Person updatePerson(Person updatedPerson) {
		return database.updatePerson(updatedPerson);
	}
}
