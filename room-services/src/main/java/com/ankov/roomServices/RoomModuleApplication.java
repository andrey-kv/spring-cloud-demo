package com.ankov.roomServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RoomModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomModuleApplication.class, args);
	}

}
