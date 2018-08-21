package com.digitalcues.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalcues.model.Person;
import com.digitalcues.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	
	private PersonService personService;
	
	
	@GetMapping("/{id}")
	public Person getPersonDetails(@PathVariable String id) {
		Person person=personService.getPersonDetails(id);
		return person;
	}
	
	
	@PostMapping
	public String savePersonDetails(@Valid @RequestBody Person person) {
		
		
		try {
		personService.save(person);
		return "PersonDetails saved successfully With Id::"+person.getPersonId();
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return "Error:Details not saved";
		
		
		
	}
	
	@PutMapping("/{id}")
	public String updatePersonDetails(@Valid @RequestBody Person person ,@PathVariable String id) {
		personService.updatePersonDetails(person,id);
		return "Person Details Updated SuccessFully!!!";
		
	}
	
	
	
	
	
	
}
