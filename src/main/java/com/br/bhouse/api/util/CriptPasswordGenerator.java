package com.br.bhouse.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptPasswordGenerator {

	public static void main(String[] args) {
		
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		System.err.println(encoder.encode("geral"));
		
		
	}
	
}
