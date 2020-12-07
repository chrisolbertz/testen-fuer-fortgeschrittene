package de.stl.saar.prog3.dao.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public interface EquipmentDao {

	Equipment deleteEquipment(long equipmentId);

	Equipment findEquipmentByPrimaryKey(long equipmentId);

	List<Equipment> findAllEquipment();

	Equipment insertEquipment(Equipment equipment);

	int countEquipments();

	Equipment updateEquipment(Equipment updatedEquipment);

	List<Equipment> findEquipmentOfPerson(Person person);

}
