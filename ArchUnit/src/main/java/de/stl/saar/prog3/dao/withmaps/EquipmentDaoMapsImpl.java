package de.stl.saar.prog3.dao.withmaps;

import java.util.List; 

import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.dao.withmaps.database.Database;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

public class EquipmentDaoMapsImpl implements EquipmentDao {
	private Database database;
	
	public EquipmentDaoMapsImpl() {
		database = Database.getInstance();
	}
	
	@Override
	public Equipment deleteEquipment(final long equipmentId){
		return database.deleteFromEquipment(equipmentId);
	}

	@Override
	public Equipment findEquipmentByPrimaryKey(final long equipmentId) {
		return database.selectFromEquipment(equipmentId);
	}
	
	@Override
	public List<Equipment> findAllEquipment() {
		return database.selectAllFromEquipment();
	}
	
	@Override
	public Equipment insertEquipment(final Equipment equipment) {
		return database.insertEquipment(equipment);
	}
	
	@Override
	public int countEquipments() {
		return database.selectCountFromEquipment();
	}

	@Override
	public Equipment updateEquipment(Equipment updatedEquipment) {
		return database.updateEquipment(updatedEquipment);
	}

	@Override
	public List<Equipment> findEquipmentOfPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
}
