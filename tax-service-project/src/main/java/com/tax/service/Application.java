package com.tax.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


//$2a$10$ick95JcOKVH7ZLub8aCFAep8SSjaQwCUfnmABF3bF5LJ7.swqnuM6
//class test {
//	public static void main(String[] args) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//		System.out.println(passwordEncoder.encode("iopiop"));
//	}
//}