package com.xyzbank.eob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EasyOnBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyOnBoardApplication.class, args);
	}

}
