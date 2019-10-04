package com.softeq.spring5hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class Spring5HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5HelloApplication.class, args);
	}

}
