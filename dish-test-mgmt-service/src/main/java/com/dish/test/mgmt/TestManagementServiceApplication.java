package com.dish.test.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@EnableDiscoveryClient
@ComponentScan(basePackages = "org.springdoc.webmvc.api")
//@ComponentScan(basePackages = "com.dish.testmanagement")
public class TestManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestManagementServiceApplication.class, args);
	}

}
