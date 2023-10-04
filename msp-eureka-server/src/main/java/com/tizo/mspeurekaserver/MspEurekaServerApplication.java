package com.tizo.mspeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MspEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspEurekaServerApplication.class, args);
	}

}
