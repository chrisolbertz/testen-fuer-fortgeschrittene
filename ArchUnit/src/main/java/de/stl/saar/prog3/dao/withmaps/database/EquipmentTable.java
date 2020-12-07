package de.stl.saar.prog3.dao.withmaps.database;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.prog3.model.interfaces.Equipment;

public class EquipmentTable {
	private Map<Long, Equipment> equipmentTable;
	
	public EquipmentTable() {
		equipmentTable = new HashMap<>();
	}
	
	public Equipment createEquipment(Equipment equipment) {
		long equipmentCount = equipmentTable.values().size();
		final Long equipmentId = ++equipmentCount;
		equipment.setEquipmentId(equipmentId);
		equipmentTable.put(equipmentId, equipment);
		return equipment;
	}
	
	public void saveEquipment(final Equipment equipment) {
		if (equipment.getEquipmentId() == 0) {
			createEquipment(equipment);
		} else {
			equipmentTable.replace(equipment.getEquipmentId(), equipment);
		}
	}

	public Equipment selectFromEquipment(long equipmentId) {
		return equipmentTable.get(equipmentId);
	}

	public Equipment deleteFromEquipment(long equipmentId) {
		final Equipment equipment = equipmentTable.get(equipmentId);
		equipmentTable.remove(equipmentId);
		return equipment;
	}

	public List<Equipment> selectAllFromEquipment() {
		final Collection<Equipment> equipmentCollection = equipmentTable.values();
		final Equipment[] equipmentArray = new Equipment[equipmentCollection.size()];
		equipmentCollection.toArray(equipmentArray);
		return Arrays.asList(equipmentArray);
	}
	
	public int selectCountFromEquipment() {
		return equipmentTable.size();
	}
}
