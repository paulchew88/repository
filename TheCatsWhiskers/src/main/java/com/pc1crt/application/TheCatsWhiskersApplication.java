package com.pc1crt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;

@SpringBootApplication
@OAuth2ContextConfiguration
public class TheCatsWhiskersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCatsWhiskersApplication.class, args);
	}

}

