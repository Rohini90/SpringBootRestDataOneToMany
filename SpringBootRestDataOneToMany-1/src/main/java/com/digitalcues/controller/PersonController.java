package com.digitalcues.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalcues.assemblers.PersonAssembler;
import com.digitalcues.model.Person;
import com.digitalcues.model.PersonDto;
import com.digitalcues.model.UpdatePersonDto;
import com.digitalcues.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	public PersonService personService;
	
	@Autowired
	public PersonAssembler personAssembler;

	public static final Logger log = LoggerFactory.getLogger(PersonController.class);

	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> getPersonDetails(@PathVariable String id) {
		log.debug("in getPersonDetails Method");
		Person person = personService.getPersonDetails(id);
		PersonDto personDto = personAssembler.toPersonDto(person);

		return new ResponseEntity<>(personDto, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<String> savePersonDetails(@Valid @RequestBody PersonDto personDto) {

		log.debug("in savePersonDetails Method");
		Person person = personAssembler.toPerson(personDto);

		Person savedPerson = personService.save(person);

		return ResponseEntity.status(HttpStatus.OK).body("Saved person details with id::" + savedPerson.getPersonId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable String id) {
		log.debug("in deletePerson Method");

		personService.delete(id);

		return ResponseEntity.status(HttpStatus.OK).body("Saved person details with id::" + id);

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePersonDetails(@Valid @RequestBody UpdatePersonDto personDto, @PathVariable String id) {
		
		log.debug("in updatePersonDetails Method");
		Person person = personAssembler.toPerson(personDto);
		personService.updatePersonDetails(person, id);

		return ResponseEntity.status(HttpStatus.OK).body("Person details updated sucessfully!!");

	}

	
  @PostMapping("/import")
 public ResponseEntity<List<Person>> findAllPersons(@RequestParam("file") MultipartFile file){
    List<Person> persons = null;

		if (!file.isEmpty()) {
			try {

				String filename = file.getOriginalFilename();

				persons = personService.readFromCsv(filename);

				return new ResponseEntity<>(persons, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(persons, HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(persons, HttpStatus.BAD_REQUEST);
		}

	 }
	 

	
	
	@GetMapping("/export")
	public ResponseEntity<Resource> mongoToCsv() {
		log.debug("in MongoTOcsv(export) Method");

		@SuppressWarnings("unused")
		List<String[]> data = personService.writeToCsv();
        
		File file = new File("E:/csv/export1.csv");
		InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (FileNotFoundException ex) {
			
			log.error("Filenotfoundxception::",ex);
		}

		return ResponseEntity.ok()

				.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);

	}

	@GetMapping("/download")
	public ResponseEntity<Resource> download() throws IOException {

		// ...
		File file = new File("export.csv");
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()

				.contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
	
	@PostMapping("/upload")

	public ResponseEntity<List<Person>> upload(@RequestParam("file") MultipartFile file) {
		List<Person> persons = null;

		if (!file.isEmpty()) {
			try {

				String filename = file.getOriginalFilename();

				persons = personService.readFromCsv(filename);

				return new ResponseEntity<>(persons, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(persons, HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(persons, HttpStatus.BAD_REQUEST);
		}
	}


}
