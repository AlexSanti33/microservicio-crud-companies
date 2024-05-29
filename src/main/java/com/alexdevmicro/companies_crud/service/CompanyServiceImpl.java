package com.alexdevmicro.companies_crud.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexdevmicro.companies_crud.entities.Category;
import com.alexdevmicro.companies_crud.entities.Company;
import com.alexdevmicro.companies_crud.repositories.CompanyRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company create(Company company) {
		
		company.getWebSites().forEach(webSite-> {
			if(Objects.isNull(webSite.getCategory()) ) {
				webSite.setCategory(Category.NONE);
			}
		});
		
		return companyRepository.save(company);
	}

	@Override
	public Company readByName(String name) {
		
		return companyRepository.findByName(name).orElseThrow(()-> new NoSuchElementException ("Company not found"));
	}

	@Override
	public Company update(Company company, String name) {
		Company companyUpdate =  companyRepository.findByName(name).orElseThrow(()-> new NoSuchElementException ("Company not found"));
		companyUpdate.setLogo(company.getLogo());
		companyUpdate.setFoundationDate(company.getFoundationDate());
		companyUpdate.setFounder(company.getFounder());
		return this.companyRepository.save(companyUpdate);
	}

	@Override
	public void delete(String name) {
		Company companyDelete =  companyRepository.findByName(name).orElseThrow(()-> new NoSuchElementException ("Company not found"));
		this.companyRepository.delete(companyDelete);
	}

	
}
