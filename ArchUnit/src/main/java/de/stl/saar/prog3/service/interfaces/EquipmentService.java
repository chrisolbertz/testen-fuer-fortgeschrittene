package de.stl.saar.prog3.service.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Equipment;

public interface EquipmentService {

	Equipment saveNewEquipment(Equipment equipment);

	List<Equipment> findAllEquipment();

	Equipment upateEquipment(Equipment updatedEquipment);

	Equipment deleteEquipment(Equipment equipment);


}
