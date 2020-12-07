package de.stl.saar.prog3.facades;

import java.util.List;

import de.stl.saar.prog3.factories.ServiceFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.service.interfaces.EquipmentService;
import de.stl.saar.prog3.service.interfaces.PersonService;

public class ServiceFacade {
	private PersonService personService;
	private CompanyService companyService;
	private EquipmentService equipmentService;
	private static ServiceFacade serviceFacade;
	
	private ServiceFacade() {
		personService = ServiceFactory.createPersonService();
		companyService = ServiceFactory.createCompanyService();
		equipmentService = ServiceFactory.createEquipmentService();
	}
	
	public Person saveNewPerson(final Person person) {
		return personService.saveNewPerson(person);
	}
	
	public Company saveNewCompany(final Company company) {
		return companyService.saveNewCompany(company);
	}
	
	public static ServiceFacade getInstance() {
		if (serviceFacade == null) {
			serviceFacade = new ServiceFacade();
		}
		
		return serviceFacade;
	}

	public void deletePerson(final Person person) {
		personService.deletePerson(person);
	}

	public List<Person> findAllPersons() {
		return personService.findAllPersons();
	}

	public List<Equipment> findAllEquipment() {
		return equipmentService.findAllEquipment();
	}

	public List<Company> findAllCompanies() {
		return companyService.findAllCompanies();
	}

	public Equipment saveNewEquipment(Equipment newEquipment) {
		return equipmentService.saveNewEquipment(newEquipment);
	}
}
