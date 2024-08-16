package com.api.common.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApiApplication.class, args);
	}

}
