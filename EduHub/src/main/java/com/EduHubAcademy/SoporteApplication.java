package com.EduHubAcademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.EduHubAcademy.soporteService")
public class SoporteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoporteApplication.class, args);
	}
}
