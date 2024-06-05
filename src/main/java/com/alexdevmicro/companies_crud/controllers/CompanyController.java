package com.alexdevmicro.companies_crud.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexdevmicro.companies_crud.entities.Company;
import com.alexdevmicro.companies_crud.service.CompanyService;

import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping(path="company")
@Slf4j
@Tag(name="companies resource")
public class CompanyController {

	private final CompanyService companyService;
	
	@Operation(summary = "get a company given a company name")
	@GetMapping(path ="{name}")
	@Observed(name="company.name")
	@Timed(value ="comapny.name")
	public ResponseEntity<Company> get(@PathVariable String name){
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		log.info("GET: company{}",name);
		return ResponseEntity.ok(this.companyService.readByName(name));
	}
	
	@Operation(summary = "Save in DB company given a company from body")
	@PostMapping

	@Observed(name="company.saved")
	@Timed(value ="comapny.saved")
	public ResponseEntity<Company>post(@RequestBody Company company){
		log.info("Post: company{}",company.getName());
		return ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();
	}
	
	
	@Operation(summary = "Update in DB company given a company from body")
	@PutMapping(path ="{name}")
	public ResponseEntity<Company> put(@RequestBody Company company,@PathVariable String name){
		log.info("PUT: company{}",name);
		return ResponseEntity.ok(this.companyService.update(company,name));
	}
	
	@Operation(summary = "Delete in DB company given a company name")
	@DeleteMapping(path="{name}")
	public ResponseEntity<?> delete(@PathVariable String name){
		log.info("PUT: company{}",name);
		this.companyService.delete(name);
		return ResponseEntity.noContent().build();
	}
}
