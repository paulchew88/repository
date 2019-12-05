package com.pc1crt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TheCatsWhiskersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCatsWhiskersApplication.class, args);
	}

}

