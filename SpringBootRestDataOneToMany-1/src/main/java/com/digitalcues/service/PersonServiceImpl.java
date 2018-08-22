package com.digitalcues.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl  implements PersonService{
	@Autowired
	public PersonRepository personRepository;

	@Override
	public Person save(Person person) {

		person.setCreatedOn(formatDate());
		person.setUpdatedOn(formatDate());
		
		return personRepository.save(person);
	}
	
     @Override
	public String delete(String id)
	{
    	 Optional<Person> person = personRepository.findById(id);
	        Person P=person.get();
	        personRepository.delete(P);
			return "person is deleted"+id;
	}



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
		updatedPersonObj.setUpdatedOn(formatDate());
		updatedPersonObj.setAddress(person.getAddress());
		personRepository.save(updatedPersonObj);
			
	}

	@Override
	public Person getPersonDetails(String id) {
		Optional<Person> personObj = personRepository.findById(id);
		Person person = personObj.get();
		
		return person;
	}

	public String formatDate() {
		Date date = new Date();
		SimpleDateFormat formDate = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		String str = formDate.format(date);
		return str;

	}

	@Override
	public boolean findByEmail(String email) {
		List <Person> list=personRepository.findAll();
		for(Person person:list)
		{
			if(person.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean findByUserName(String userName) {
		List<Person> list=personRepository.findAll();
		for(Person person:list)
		{
			if(person.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}
	
	/*public Date parseDate(Date date) {
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
	    // myDate is the java.util.Date in yyyy-mm-dd format
	    // Converting it into String using formatter
	    String strDate = sm.format(date);
	    //Converting the String back to java.util.Date
	    
	    try {
			 Date date1= sm.parse(strDate);
			 return date1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		}*/
	  


}
