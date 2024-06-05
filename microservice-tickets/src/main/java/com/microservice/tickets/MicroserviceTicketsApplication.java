package com.microservice.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceTicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTicketsApplication.class, args);
	}

}
