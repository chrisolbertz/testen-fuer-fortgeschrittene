package de.stl.saar.prog3.service.implementation;

import java.util.List;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.factories.DaoFactory;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.service.interfaces.CompanyService;

public class CompanyServiceImpl implements CompanyService {
	private CompanyDao companyDao;
	
	public CompanyServiceImpl() {
		companyDao = DaoFactory.createCompanyDao();
	}
	
	@Override
	public Company saveNewCompany(final Company company) {
		Company newCompany = companyDao.insertCompany(company);
		return newCompany;
	}
	
	@Override
	public List<Company> findAllCompanies() {
		return companyDao.findAllCompanies();
	}
	
	@Override
	public Company upateCompany(final Company updatedCompany) {
		return companyDao.updateCompany(updatedCompany);
	}
	
	@Override
	public Company deleteCompany(final Company company) {
		return companyDao.deleteCompany(company.getCompanyId());
	}
}
