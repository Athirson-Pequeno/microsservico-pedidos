package com.tizo.mspauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MspAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspAuthApplication.class, args);
	}

}
