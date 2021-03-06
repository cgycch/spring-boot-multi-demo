package org.springboot.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {
	public static void main(String[] args) {
		System.out.println("Service Application is start...");
		SpringApplication.run(ServiceApplication.class, args);
		System.out.println("Service Application is running...");
	}
}
