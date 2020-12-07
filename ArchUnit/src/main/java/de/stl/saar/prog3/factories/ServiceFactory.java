package de.stl.saar.prog3.factories;

import de.stl.saar.prog3.service.implementation.CompanyServiceImpl;
import de.stl.saar.prog3.service.implementation.EquipmentServiceImpl;
import de.stl.saar.prog3.service.implementation.PersonServiceImpl;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.service.interfaces.EquipmentService;
import de.stl.saar.prog3.service.interfaces.PersonService;

public class ServiceFactory {

	public static PersonService createPersonService() {
		return new PersonServiceImpl();
	}
	
	public static EquipmentService createEquipmentService() {
		return new EquipmentServiceImpl();
	}

	public static CompanyService createCompanyService() {
		return new CompanyServiceImpl();
	}
}
