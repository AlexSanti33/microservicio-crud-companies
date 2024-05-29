package com.alexdevmicro.companies_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexdevmicro.companies_crud.entities.WebSite;

public interface WebSiteRepository extends JpaRepository<WebSite, Long>{
	

}
