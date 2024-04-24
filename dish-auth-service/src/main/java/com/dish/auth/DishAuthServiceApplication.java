package com.dish.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@CrossOrigin
@OpenAPIDefinition
@EnableDiscoveryClient
public class DishAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishAuthServiceApplication.class, args);
	}

}
