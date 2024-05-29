package com.alexdevmicro.companies_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexdevmicro.companies_crud.models.Prueba;

@SpringBootApplication
public class CompaniesCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniesCrudApplication.class, args);
		
		Prueba p = new Prueba();
		p.setPrueba("Prueba");
		p.setSinPrueba("");
	}

}
