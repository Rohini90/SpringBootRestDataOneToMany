package com.digitalcues.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.digitalcues.model.Person;

public interface PersonRepository  extends MongoRepository<Person,String>{
	

}
