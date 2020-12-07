package de.stl.saar.prog3.dao.withmaps.database;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.stl.saar.prog3.model.interfaces.Company;

public class CompanyTable {
	private Map<Long, Company> companyTable;
	
	public CompanyTable() {
		companyTable = new HashMap<>();
	}
	
	public Company createCompany(final Company company) {
		long companyCount = companyTable.values().size();
		final Long companyId = ++companyCount;
		company.setCompanyId(companyId);
		//final long companyId = company.getCompanyId();
		companyTable.put(companyId, company);
		return company;
	}
	
	public Company saveCompany(final Company company) {
		Company newCompany = company;
		if (company.getCompanyId() == 0) {
			newCompany = createCompany(company);
		} else {
			companyTable.replace(company.getCompanyId(), company);
		}
		
		return newCompany;
	}

	public Company selectFromCompany(long companyId) {
		return companyTable.get(companyId);
	}

	public Company deleteFromCompany(long companyId) {
		final Company company = companyTable.get(companyId);
		companyTable.remove(companyId);
		return company;
	}

	public List<Company> selectAllFromCompany() {
		final Collection<Company> companyCollection = companyTable.values();
		final Company[] companyArray = new Company[companyCollection.size()];
		companyCollection.toArray(companyArray);
		return Arrays.asList(companyArray);
	}
	
	/*public Company selectCompanyByCompanyName(final String companyName) {
		final List<Company> companys = selectAllFromCompany();
		
		for(final Company company: companys) {
			if (StringUtils.areStringsEqual(company.getCompanyName(), companyName)) {
				return company;
			}
		}
		
		return null;
	}*/

	public int selectCountFromCompany() {
		return companyTable.size();
	}
}
