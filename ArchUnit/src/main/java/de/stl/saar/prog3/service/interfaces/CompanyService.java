package de.stl.saar.prog3.service.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Company;

public interface CompanyService {

	Company saveNewCompany(Company company);

	List<Company> findAllCompanies();

	Company upateCompany(Company updatedCompany);

	Company deleteCompany(Company company);

}
