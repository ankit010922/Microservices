package com.dish.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DishApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishApiGatewayApplication.class, args);
	}

}
