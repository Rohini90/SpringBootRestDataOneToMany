package com.digitalcues.service;


import java.util.Optional;

import com.digitalcues.model.Person;


public interface PersonService {
 
	public Person save(Person person);
//<<<<<<< HEAD
	 
	public String delete(String personId);

	public boolean findByEmail(String email);

	
//=======
	public void updatePersonDetails(Person person,String id);
	public Person getPersonDetails(String id);
//>>>>>>> f29102752640cb56d2c27ea7f158d3ca8e257f9b

	public boolean findByUserName(String userName);
	
}
