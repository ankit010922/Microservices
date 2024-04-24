package com.example.dishdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DishDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishDiscoveryServiceApplication.class, args);
	}

}
