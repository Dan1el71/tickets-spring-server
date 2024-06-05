package com.microservice.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAuthenticationApplication.class, args);
	}

}
