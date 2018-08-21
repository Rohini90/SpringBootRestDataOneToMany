package com.digitalcues.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.digitalcues.controller","com.digitalcues.model"})
@EnableMongoRepositories("com.digitalcues.repository")
public class SpringBootRestDataOneToMany1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestDataOneToMany1Application.class, args);
	}
}
