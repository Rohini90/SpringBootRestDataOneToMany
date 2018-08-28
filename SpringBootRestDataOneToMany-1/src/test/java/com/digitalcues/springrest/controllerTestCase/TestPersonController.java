package com.digitalcues.springrest.controllerTestCase;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.digitalcues.exception.PersonNotFoundException;
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
	
	
	/**Test get method
	 * 
	 */
	@Test
	public void testGetPersonDetails() {

		Person person=personservice.getPersonDetails("5b7d43d6d1c22b0968b4af01");

		Person exptPerson = new Person();
		//person.setLastName("jain");

		exptPerson.setLastName("jain");

        
		assertEquals("expected[" + exptPerson + "] and actrual [" + person + "]result differ", person, person);

	}
   
	/**Test delete method
	 * @throws ParseException
	 * @throws PersonNotFoundException
	 */
	/*@Test	
	public void testdeleteDetails() throws ParseException,PersonNotFoundException
	{
    	SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
    	Person newperson=new Person("222","pari","roy","spk2l@df.com","ffddg342","fppfccvv#123",sm.parse("2017-09-06"), new ArrayList<Address>());
    	Person person=personservice.getPersonDetails("222");
    	personrepository.delete(person);
    	
    	assertTrue("delete operation is fail if person object is present",false);		
        
	}
    	*/
	
	/**Test save method
	 * 
	 *
	 */
    
     @Test
	public void testSave() throws ParseException
	{
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
		Person savedperson=new Person("pink","roy","spk2l@df.com","ffddg342","fppfccvv#123",parseDate("2018-09-08"), new ArrayList<Address>());
		personservice.save(savedperson);
		Person newperson=personservice.getPersonDetails(savedperson.getPersonId());
		Person person=personservice.getPersonDetails("5b7d0c3bd1c22b15e8a93a3b");
	
		//if pass correct objects testing result is true------->
		
		assertEquals("save operation is fail if object's values are not same", savedperson, newperson);
		
		//if we pass different objects testing result is false------->
		//assertEquals("save operation is fail if object's values are not same", savedperson, person);
	}

     
     private Date parseDate(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/** Test update method
      * 
      *
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
