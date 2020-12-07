package de.stl.saar.prog3.view.fx.model;

import de.stl.saar.prog3.factories.ModelFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.utils.StringUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyFx {
	private LongProperty companyId;
	private StringProperty companyName;
	private IntegerProperty employeeCount;
	private Company company;
	
	public CompanyFx() {
		companyName = new SimpleStringProperty(StringUtils.EMPTY_STRING);
		companyId = new SimpleLongProperty(0);
		employeeCount = new SimpleIntegerProperty(0); 
		this.company = ModelFactory.createCompany();
	}
	
	public CompanyFx(final Company company) {
		companyName = new SimpleStringProperty(company.getCompanyName());
		companyId = new SimpleLongProperty(company.getCompanyId());
		employeeCount = new SimpleIntegerProperty(company.getEmployeesCount());
		this.company = company;
	}
	
	public String getCompanyName() {
		return companyName.get();
	}
	
	public void setCompanyName(String companyName) {
		company.setCompanyName(companyName);
		this.companyName.set(companyName);
	}
	
	public StringProperty companyNameProperty() {
		return companyName;
	}
	
	public Long getCompanyId() {
		return companyId.get();
	}
	
	public LongProperty companyIdProperty() {
		return companyId;
	}
	
	public void setCompanyId(final long companyId) {
		company.setCompanyId(companyId);
		this.companyId.set(companyId);
	}
	
	public IntegerProperty employeeCountProperty() {
		return employeeCount;
	}
	
	public int getEmployeeCount() {
		return employeeCount.get();
	}
	
	public void setEmployeeCount(final int employeesCount) {
		company.setEmployeesCount(employeesCount);
		this.employeeCount.set(employeesCount);
	}
	
	public Company getCompany() {
		return company;
	}
	
	@Override
	public String toString() {
		return companyName.get();
	}
}
