package de.stl.saar.prog3.factories;

import java.util.ArrayList;
import java.util.List;

import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import de.stl.saar.prog3.view.fx.model.PersonFx;

public class FxModelFactory {
	public static PersonFx createPersonFx(final Person person) {
		final PersonFx personFx = new PersonFx(person);
		return personFx;
	}
	
	public static PersonFx createPersonFx() {
		final PersonFx personFx = new PersonFx();
		return personFx;
	}
	
	public static List<PersonFx> createPersonFxList(
			final List<Person> personList) {
		final List<PersonFx> personFxList = new ArrayList<>();
		for (final Person person: personList) {
			final PersonFx personFx = createPersonFx(person);
			personFxList.add(personFx);
		}
		
		return personFxList;
	}
	
	public static EquipmentFx createEquipmentFx() {
		final EquipmentFx equipmentFx = new EquipmentFx();
		return equipmentFx;
	}
	
	public static EquipmentFx createEquipmentFx(final Equipment equipment) {
		final EquipmentFx equipmentFx = new EquipmentFx(equipment);
		return equipmentFx;
	}
	
	public static List<EquipmentFx> createEquipmentFxList(final List<Equipment> equipmentList) {
		final List<EquipmentFx> equipmentFxList = new ArrayList<>();
		for (final Equipment equipment: equipmentList) {
			final EquipmentFx equipmentFx = createEquipmentFx(equipment);
			equipmentFxList.add(equipmentFx);
		}
		
		return equipmentFxList;
	}
	
	public static CompanyFx createCompanyFx() {
		final CompanyFx companyFx = new CompanyFx();
		return companyFx;
	}
	
	public static CompanyFx createCompanyFx(final Company company) {
		final CompanyFx companyFx = new CompanyFx(company);
		return companyFx;
	}

	public static List<CompanyFx> createCompanyFxList(List<Company> companies) {
		final List<CompanyFx> companyFxList = new ArrayList<>();
		for (final Company company: companies) {
			final CompanyFx companyFx = createCompanyFx(company);
			companyFxList.add(companyFx);
		}
		
		return companyFxList;
	}
}
