package com.alexdevmicro.companies_crud.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Companies CRUD",
				version ="1.0",
				description = "This is a crud for managment company"
				)
		
		)
public class OpenApiConfig {

}
