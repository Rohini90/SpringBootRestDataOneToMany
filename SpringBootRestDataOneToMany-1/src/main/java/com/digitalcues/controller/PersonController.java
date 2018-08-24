package com.digitalcues.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.digitalcues.model.Employee;
import com.digitalcues.model.Person;
import com.digitalcues.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	public PersonService personService;
	
	public static final Logger log = LoggerFactory.getLogger(PersonController.class);

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonDetails(@PathVariable String id) {
		Person person = personService.getPersonDetails(id);

		return new ResponseEntity<Person>(person,HttpStatus.OK);
		
	}

/*	@PostMapping
	public ResponseEntity<?> savePersonDetails(@Valid @RequestBody Person person ,BindingResult bindingResult) throws NoSuchMethodException, SecurityException, MethodArgumentNotValidException {
		
		if(bindingResult.hasErrors()) {
			 throw new MethodArgumentNotValidException(
		                new MethodParameter(this.getClass().getDeclaredMethod("savePersonDetails", PersonController.class), 0),
		                bindingResult);
		}

		Person savedPerson = personService.save(person);
	
		return ResponseEntity.status(HttpStatus.OK).body("Saved person details with id::"+savedPerson.getPersonId());


	}
*/
	@PostMapping
public ResponseEntity<?> savePersonDetails(@Valid @RequestBody Person person )  {
		
		

		Person savedPerson = personService.save(person);
	
		return ResponseEntity.status(HttpStatus.OK).body("Saved person details with id::"+savedPerson.getPersonId());
}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable String id) {
		
			personService.delete(id);
			
			return ResponseEntity.status(HttpStatus.OK).body("Saved person details with id::"+id);
		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePersonDetails(@Valid @RequestBody Person person, @PathVariable String id) {

		personService.updatePersonDetails(person, id);

		return ResponseEntity.status(HttpStatus.OK).body("Person details updated sucessfully!!");

	}
	
	@GetMapping("/import")
	public ResponseEntity<List<Person>> findAllPersons() {
		List<Person>persons= personService.findAll();
		return new ResponseEntity<List<Person>>(persons,HttpStatus.OK);
		
	}
	

}
