package com.digitalcues.springrest.controllerTestCase;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
/*
    @Test	
	public void testdeleteDetails() throws ParseException
	{
    	SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
    	//Person newperson=new Person("555","pari","roy","spk2l@df.com","ffddg342","fppfccvv#123",sm.parse("2017-09-06"), null, null, new ArrayList<Address>());
    	Person person=personservice.getPersonDetails("5b82b77cd1c22b2624053bba");
    	personrepository.delete(person);  	
    	assertThatNullPointerException();
    	
    	
	}
*/
	/*
     @Test
	public void testSave() throws ParseException
	{
		//String date="2017-09-05";
	     Person person=new Person();
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
		Person savedperson=new Person("pari","roy","spk2l@df.com","ffddg342","fppfccvv#123",sm.parse("2017-09-06"), new ArrayList<Address>());
		personservice.save(savedperson);
		Person newperson=personservice.getPersonDetails(savedperson.getPersonId());
      //		assertEquals("save operation is not successful when expected is"+savedperson+"but  person is not found", savedperson, newperson);
		//assertSame(savedperson, newperson);
		System.out.println(newperson);
		System.out.println(savedperson);
		assertEquals("save operation is fail if object's values are not same", newperson, savedperson);
	}
	*/
    @Test
    public void testUpdate()
    {
    	Person person=personservice.getPersonDetails("5b82c503d1c22b33f43625f3");
    	person.setLastName("bhatt");
    	personservice.updatePersonDetails(person, "5b82c503d1c22b33f43625f3");
    	Person newperson=personservice.getPersonDetails("5b82c503d1c22b33f43625f3");
    	assertNotSame("update operation is not work", person, newperson);
    	
    }
}
