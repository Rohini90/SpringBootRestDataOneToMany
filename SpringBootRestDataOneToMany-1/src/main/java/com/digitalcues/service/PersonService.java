package com.digitalcues.service;


import com.digitalcues.model.Person;


public interface PersonService {
 
	public void save(Person person);
	public void updatePersonDetails(Person person,String id);
	public Person getPersonDetails(String id);
	
}
