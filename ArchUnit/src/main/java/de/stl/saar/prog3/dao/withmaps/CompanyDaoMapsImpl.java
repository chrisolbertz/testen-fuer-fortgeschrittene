package de.stl.saar.prog3.dao.withmaps;

import java.util.List;

import de.stl.saar.prog3.dao.interfaces.CompanyDao;
import de.stl.saar.prog3.dao.withmaps.database.Database;
import de.stl.saar.prog3.model.interfaces.Company;

public class CompanyDaoMapsImpl implements CompanyDao {
	private Database database;
	
	public CompanyDaoMapsImpl() {
		database = Database.getInstance();
	}
	
	@Override
	public Company deleteCompany(final long companyId){
		return database.deleteFromCompany(companyId);
	}

	@Override
	public Company findCompanyByPrimaryKey(final long companyId) {
		return database.selectFromCompany(companyId);
	}
	
	@Override
	public List<Company> findAllCompanies() {
		return database.selectAllFromCompany();
	}
	
	@Override
	public Company insertCompany(final Company company) {
		return database.insertCompany(company);
	}
	
	@Override
	public int countCompanies() {
		return database.selectCountFromCompany();
	}

	@Override
	public Company updateCompany(Company updatedCompany) {
		return database.updateCompany(updatedCompany);
	}

	@Override
	public Company findCompanyByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}
}
