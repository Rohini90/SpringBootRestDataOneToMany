package com.digitalcues.service;

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



}
