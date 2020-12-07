package de.stl.saar.prog3.model.classes;

import de.stl.saar.prog3.model.interfaces.Company;

public class CompanyImpl implements Company {
	private long companyId;
	private String companyName;
	private int employeesCount;
	
	@Override
	public long getCompanyId() {
		return companyId;
	}
	
	@Override
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	@Override
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
		return "ID: " + companyId + " Fimenname: " + companyName + " Mitarbeiter: " + employeesCount;
	}
}
