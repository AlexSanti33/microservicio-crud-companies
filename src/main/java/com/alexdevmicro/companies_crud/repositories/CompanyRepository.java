package com.alexdevmicro.companies_crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexdevmicro.companies_crud.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	Optional<Company>findByName(String name);

}
