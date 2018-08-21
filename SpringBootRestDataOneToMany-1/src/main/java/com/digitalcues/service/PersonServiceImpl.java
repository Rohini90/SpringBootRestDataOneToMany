package com.digitalcues.service;

//<<<<<<< HEAD
//=======
import java.util.Date;
//>>>>>>> f29102752640cb56d2c27ea7f158d3ca8e257f9b
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl  implements PersonService{
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void save(Person person) {
		
		person.setCreatedOn(new Date());
		person.setUpdatedOn(new Date());
		personRepository.save(person);
	}
	
     @Override
	public String delete(String id)
	{
    	 Optional<Person> person = personRepository.findById(id);
	        Person P=person.get();
	        personRepository.delete(P);
			return "person is deleted"+id;
	}


/*
	@Override
	public void updatePersonDetails(Person person,String id) {
		Optional<Person> personObj = personRepository.findById(id);
		Person updatedPersonObj = personObj.get();
		updatedPersonObj.setFirstName(person.getFirstName());
		updatedPersonObj.setLastName(person.getLastName());
		updatedPersonObj.setFirstName(person.getFirstName());
		updatedPersonObj.setEmail(person.getEmail());
		updatedPersonObj.setUserName(person.getUserName());
		updatedPersonObj.setPassword(person.getPassword());
		updatedPersonObj.setFirstName(person.getFirstName());
		updatedPersonObj.setJoiningDate(person.getJoiningDate());
		updatedPersonObj.setUpdatedOn(new Date());
		updatedPersonObj.setAddress(person.getAddress());
		personRepository.save(updatedPersonObj);
			
	}

	@Override
	public Person getPersonDetails(String id) {
		Optional<Person> personObj = personRepository.findById(id);
		Person person = personObj.get();
		
		return person;
	}

	
	*/

}
