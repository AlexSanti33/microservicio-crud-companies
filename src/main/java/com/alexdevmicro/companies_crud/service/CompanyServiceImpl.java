package com.alexdevmicro.companies_crud.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexdevmicro.companies_crud.entities.Category;
import com.alexdevmicro.companies_crud.entities.Company;
import com.alexdevmicro.companies_crud.repositories.CompanyRepository;

import io.micrometer.tracing.Tracer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	private final Tracer tracer;
	
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
		
		var spam = tracer.nextSpan().name("readByName");
		try(Tracer.SpanInScope inScope = this.tracer.withSpan(spam.start())){
			log.info("Getting comany from db");
		}finally {
			spam.end();
		}
		
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
