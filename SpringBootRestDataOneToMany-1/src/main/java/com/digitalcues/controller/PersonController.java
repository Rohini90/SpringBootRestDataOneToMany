package com.digitalcues.controller;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitalcues.model.Person;
import com.digitalcues.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	public PersonService personService;

	@GetMapping("/{id}")
	public Person getPersonDetails(@PathVariable String id) {
		Person person = personService.getPersonDetails(id);

		return person;
	}

	@PostMapping
	public ResponseEntity<Object> savePersonDetails(@Valid @RequestBody Person person) {

		Person savedPerson = personService.save(person);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerson.getPersonId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable String id) {
		
			personService.delete(id);

		
		return " person details  with id" + id + "deleted successfully!!!!!";
	}

	/*
	 * @PutMapping("/{id}") public String updatePersonDetails(@Valid @RequestBody
	 * Person person ,@PathVariable String id) {
	 * personService.updatePersonDetails(person,id); return
	 * "Person Details Updated SuccessFully!!!";
	 * 
	 * }
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePersonDetails(@Valid @RequestBody Person person, @PathVariable String id) {

		personService.updatePersonDetails(person, id);

		return ResponseEntity.status(HttpStatus.OK).body("Person details updated sucessfully!!");

	}

}
