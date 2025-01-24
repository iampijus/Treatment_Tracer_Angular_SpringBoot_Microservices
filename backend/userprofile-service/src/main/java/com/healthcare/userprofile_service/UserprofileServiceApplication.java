package com.healthcare.userprofile_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserprofileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserprofileServiceApplication.class, args);
	}

}
