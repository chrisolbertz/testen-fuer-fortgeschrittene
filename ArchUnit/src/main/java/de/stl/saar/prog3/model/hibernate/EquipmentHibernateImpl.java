package de.stl.saar.prog3.model.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import de.stl.saar.prog3.model.interfaces.Equipment;

@Entity
@Table(name = "equipment")
public class EquipmentHibernateImpl implements Equipment {
	private long equipmentId;
	private String equipmentName;
	private double weight;
	//private String equipmentDescription;
	
	public EquipmentHibernateImpl() {
	}
	
	@Override
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	/*@Lob
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}*/
}
