package com.digitalcues.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;
import com.digitalcues.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	
	private PersonService personService;
	
	
	@PostMapping("/persons")
	public String setPersonDetails(@Valid @RequestBody Person person) {
		person.setCreatedOn(new Date());
		person.setUpdatedOn(new Date());
		
		try {
		personService.save(person);
		return "dPersonDetails saved successfully With Id::"+person.getPersonId();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}
		
		@GetMapping("/delete/{id}") 
		String deletePerson(@PathVariable String id)
		{
			try {
				personService.delete(id);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return"one person is deleted with id"+id;
		}
		
		
	}
	

