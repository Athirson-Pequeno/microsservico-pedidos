package com.tizo.msporders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MspOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspOrdersApplication.class, args);
	}

}
