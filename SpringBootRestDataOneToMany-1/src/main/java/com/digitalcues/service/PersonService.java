package com.digitalcues.service;


import java.util.Optional;

import com.digitalcues.model.Person;


public interface PersonService {
 
	public void save(Person person);
	 
	public String delete(String personId);

	
	
}
