package com.godev.atomus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AtomusApplication {
	public static void main(String[] args) {
		SpringApplication.run(AtomusApplication.class, args);
	}
}
