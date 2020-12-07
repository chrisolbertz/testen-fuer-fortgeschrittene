package de.stl.saar.prog3.view.fx.model;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.utils.StringUtils;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EquipmentFx {
	private LongProperty equipmentId;
	private StringProperty equipmentName;
	private DoubleProperty weight;
	private Equipment equipment;
	
	public EquipmentFx() {
		equipmentName = new SimpleStringProperty(StringUtils.EMPTY_STRING);
		equipmentId = new SimpleLongProperty(0);
		weight = new SimpleDoubleProperty(0.0); 
		equipment = ModelFactory.createEquipment();
	}
	
	public EquipmentFx(final Equipment equipment) {
		equipmentName = new SimpleStringProperty(equipment.getEquipmentName());
		equipmentId = new SimpleLongProperty(equipment.getEquipmentId());
		weight = new SimpleDoubleProperty(equipment.getWeight());
		this.equipment = equipment;
	}
	
	public String getEquipmentName() {
		return equipmentName.get();
	}
	
	public StringProperty equipmentNameProperty() {
		return equipmentName;
	}
	
	public void setEquipmentName(String equipmentName) {
		equipment.setEquipmentName(equipmentName);
		this.equipmentName.set(equipmentName);
	}
	
	public Long getEquipmentId() {
		return equipmentId.get();
	}
	
	public LongProperty equipmentIdProperty() {
		return equipmentId;
	}
	
	public void setEquipmentId(final long equipmentId) {
		this.equipment.setEquipmentId(equipmentId);
		this.equipmentId.set(equipmentId);
	}
	
	public DoubleProperty weightProperty() {
		return weight;
	}
	
	public double getWeight() {
		return weight.get();
	}
	
	public void setWeight(final double weight) {
		equipment.setWeight(weight);
		this.weight.set(weight);
	}
	
	public Equipment getEquipment() {
		return equipment;
	}
	
	@Override
	public String toString() {
		return equipmentName.get();
	}
}
