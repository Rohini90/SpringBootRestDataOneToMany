package com.digitalcues.service;


import java.util.List;

import com.digitalcues.model.Person;

public interface PersonService {

	public boolean findByEmail(String email);

	public List<Person> readFromCsv(String filename);

	public boolean findByUserName(String userName);

	public Person save(Person person);

	public String delete(String personId);

	public void updatePersonDetails(Person person, String id);

	public Person getPersonDetails(String id);

	public List<String[]> writeToCsv();

}
