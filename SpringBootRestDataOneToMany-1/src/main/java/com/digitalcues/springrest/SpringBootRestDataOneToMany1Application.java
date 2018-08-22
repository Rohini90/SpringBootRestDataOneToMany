package com.digitalcues.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan("com.digitalcues.model")
@ComponentScan({"com.digitalcues.controller","com.digitalcues.model","com.digitalcues.service"})
@EnableMongoRepositories("com.digitalcues.repository")
@PropertySource("classpath:application.properties")
public class SpringBootRestDataOneToMany1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestDataOneToMany1Application.class, args);
	}
}
