package de.stl.saar.prog3.model.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.stl.saar.prog3.model.interfaces.Company;

@Entity
@Table(name = "company")
public class CompanyHibernateImpl implements Company {
	private long companyId;
	private String companyName;
	private int employeesCount;
	//private Country country;
	//private Calendar date;
	//private List<Person> persons;
	
	public CompanyHibernateImpl() {
	}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCompanyId() {
		return companyId;
	}
	
	@Override
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	@Override
	//@Column(name = "companyName", 
		//	nullable = false)
	public String getCompanyName() {
		return companyName;
	}
	
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
	public int getEmployeesCount() {
		return employeesCount;
	}
	
	@Override
	public void setEmployeesCount(int employeesCount) {
		this.employeesCount = employeesCount;
	}
	
	@Override
	public String toString() {
		return companyName;
	}
	
	/*@Enumerated(EnumType.STRING)
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Temporal(TemporalType.DATE)
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	@Override
	@OneToMany(targetEntity = PersonHibernateImpl.class,
			   mappedBy = "company")
	public List<Person> getPersons() {
		return persons;
	}
	
	@Override
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}*/
}
