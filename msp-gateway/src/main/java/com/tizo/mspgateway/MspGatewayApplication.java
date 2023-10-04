package com.tizo.mspgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MspGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspGatewayApplication.class, args);
	}

}
