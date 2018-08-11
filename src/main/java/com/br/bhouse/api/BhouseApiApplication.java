package com.br.bhouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.br.bhouse.api.config.property.BhouseApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(BhouseApiProperty.class)
public class BhouseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BhouseApiApplication.class, args);
	}
}
