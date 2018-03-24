package com.br.bhouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BhouseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BhouseApiApplication.class, args);
	}
}
