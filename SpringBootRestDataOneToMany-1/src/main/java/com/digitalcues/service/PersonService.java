package com.digitalcues.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.digitalcues.model.Employee;
import com.digitalcues.model.Person;

public interface PersonService {


	public boolean findByEmail(String email);

	 public List<Person> findAll();


	public boolean findByUserName(String userName);


	public Person save(Person person);

	public String delete(String personId);

	public void updatePersonDetails(Person person, String id);

	public Person getPersonDetails(String id);
    

	
}
