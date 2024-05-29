package com.alexdevmicro.companies_crud.service;

import com.alexdevmicro.companies_crud.entities.Company;

public interface CompanyService {

	Company create(Company company);
	Company readByName(String name);
	Company update(Company company,String name);
	void delete(String name);
}
