package com.digitalcues.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.digitalcues.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	
	@SuppressWarnings("unchecked")
	public Person save(Person person);
	
	public Optional<Person> findById(String id);
	
	
	
	
		
	

}
