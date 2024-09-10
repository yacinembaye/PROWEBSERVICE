package com.example.bank_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Bank API", version = "1.0", description = "API for managing bank accounts and transactions"))
public class BankApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}
}