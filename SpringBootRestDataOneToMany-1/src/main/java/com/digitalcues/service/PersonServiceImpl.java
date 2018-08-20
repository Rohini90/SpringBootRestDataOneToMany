package com.digitalcues.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
