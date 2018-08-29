package com.digitalcues.assemblers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.digitalcues.model.Person;
import com.digitalcues.model.PersonDto;
import com.digitalcues.model.UpdatePersonDto;

@Component
public class PersonAssembler {
	Logger logger = LoggerFactory.getLogger( PersonAssembler.class);
	
	public Person toPerson(PersonDto personDto) {
		logger.debug("in toPerson method");
		Person  person= new Person();
		person.setFirstName(personDto.getfName());
		person.setLastName(personDto.getlName());
		person.setEmail(personDto.getEmail());
		person.setUserName(personDto.getUserName());
		person.setPassword(personDto.getPassword());
		person.setJoiningDate(personDto.getJoiningDate());
		person.setAddress(personDto.getAddress());
		
		return person;
		
	}
	
	public Person toPerson(UpdatePersonDto personDto) {
		Person  person= new Person();
		person.setPersonId(personDto.getId());
		person.setFirstName(personDto.getfName());
		person.setLastName(personDto.getlName());
		person.setEmail(personDto.getEmail());
		person.setUserName(personDto.getUserName());
		person.setPassword(personDto.getPassword());
		person.setJoiningDate(personDto.getJoiningDate());
		person.setAddress(personDto.getAddress());
		
		return person;
		
	}
	
	public PersonDto toPersonDto(Person person) {
		
		PersonDto personDto = new PersonDto();
		personDto.setId(person.getPersonId());
		personDto.setfName(person.getFirstName());
		personDto.setlName(person.getLastName());
		personDto.setEmail(person.getEmail());
		personDto.setUserName(person.getUserName());
		personDto.setPassword(person.getPassword());
		personDto.setJoiningDate(person.getJoiningDate());
		personDto.setAddress(person.getAddress());
		
		
		
		return personDto;
		
	}


}
