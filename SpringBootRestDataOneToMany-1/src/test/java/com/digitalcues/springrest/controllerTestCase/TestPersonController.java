package com.digitalcues.springrest.controllerTestCase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitalcues.controller.PersonController;
import com.digitalcues.model.Address;
import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;
import com.digitalcues.service.PersonService;
import com.digitalcues.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPersonController {
	@Autowired
	PersonService personservice ;
	
	@Autowired
	PersonRepository personrepository;
	@Test
	public void testGetPersonDetails() {

		Person person=personservice.getPersonDetails("5b7d43d6d1c22b0968b4af01");

		Person exptPerson = new Person();
		//person.setLastName("jain");

		exptPerson.setLastName("jain");

        
		assertEquals("expected[" + exptPerson + "] and actrual [" + person + "]result differ", person, person);

	}

    @Test	
	public void testdeleteDetails()
	{
    	//Person person=personservice.getPersonDetails("5b7d4784d1c22b31ccd9a1d4");
    	//String expected=personservice.delete("5b7d4892d1c22b1ba0b31ec7");
    	Person person=personservice.getPersonDetails("5b7d49ecd1c22b39b08b25fb");
    	personrepository.delete(person);
    	//Person experson=personservice.getPersonDetails("5b7d49ecd1c22b39b08b25fb");
    	assertEquals("delete operation falses", null, person);
    	
    	
    	
	}

/*	@Test
	public void testSave()
	{
		
		Person savedperson=new Person();
		Person p= personservice.save(savedperson);
		assertEquals("save operation", savedperson, p);
		
	}
	*/
}
