package de.stl.saar.prog3.model.hibernate;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;

@Entity
@Table(name = "person")
public class PersonHibernateImpl implements Person {
	private long personId;
	private String firstName;
	private String lastName;
	private Company company;
	private List<Equipment> equipment;
	
	public PersonHibernateImpl() {
		//equipment = new ArrayList<>();
	}
	
	@Override
	public Company getCompany() {
		return company;
	}
	
	@Override
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getPersonId() {
		return personId;
	}
	
	@Override
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	//@Column(unique = true, 
		//	length = 50)
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	@ManyToMany(targetEntity = EquipmentHibernateImpl.class)
	public List<Equipment> getEquipment() {
		return equipment;
	}
	
	@Override
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}
	
	/*@Override
	@Transient
	public String getInitials() {
		String initials = String.valueOf(firstName.charAt(0));
		initials = initials + String.valueOf(lastName.charAt(0));
		return initials;
	}*/
	
	@Override
	public void addEquipment(final Equipment oneEquipment) {
		equipment.add(oneEquipment);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (personId ^ (personId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonHibernateImpl other = (PersonHibernateImpl) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (personId != other.personId)
			return false;
		return true;
	}
}
