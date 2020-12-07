package de.stl.saar.prog3.model.jdbc;

import de.stl.saar.prog3.model.interfaces.Equipment;

public class EquipmentJdbcImpl implements Equipment {
	private long equipmentId;
	private String equipmentName;
	private double weight;
	
	@Override
	public long getEquipmentId() {
		return equipmentId;
	}
	
	@Override
	public void setEquipmentId(long equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Override
	public String getEquipmentName() {
		return equipmentName;
	}
	
	@Override
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Override
	public double getWeight() {
		return weight;
	}
	
	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return equipmentName;
	}
}
