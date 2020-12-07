package de.stl.saar.prog3.service.implementation;

import java.util.List;

import de.stl.saar.prog3.dao.interfaces.EquipmentDao;
import de.stl.saar.prog3.factories.DaoFactory;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.service.interfaces.EquipmentService;

public class EquipmentServiceImpl implements EquipmentService {
	private EquipmentDao equipmentDao;
	
	public EquipmentServiceImpl() {
		equipmentDao = DaoFactory.createEquipmentDao();
	}
	
	@Override
	public Equipment saveNewEquipment(final Equipment equipment) {
		Equipment newEquipment = equipmentDao.insertEquipment(equipment);
		return newEquipment;
	}
	
	@Override
	public List<Equipment> findAllEquipment() {
		return equipmentDao.findAllEquipment();
	}
	
	@Override
	public Equipment upateEquipment(final Equipment updatedEquipment) {
		return equipmentDao.updateEquipment(updatedEquipment);
	}
	
	@Override
	public Equipment deleteEquipment(final Equipment equipment) {
		return equipmentDao.deleteEquipment(equipment.getEquipmentId());
	}
}
