package de.stl.saar.prog3.dao.interfaces;

import java.util.List;

import de.stl.saar.prog3.model.interfaces.Company;

public interface CompanyDao {

	Company deleteCompany(long companyId);

	Company findCompanyByPrimaryKey(long companyId);

	List<Company> findAllCompanies();

	Company insertCompany(Company company);

	int countCompanies();

	Company updateCompany(Company updatedCompany);

	Company findCompanyByCompanyName(String companyName);
}
