package com.digitalcues.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.Email;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalcues.controller.PersonController;
import com.digitalcues.exception.PersonNotFoundException;
import com.digitalcues.model.Address;
import com.digitalcues.model.Employee;
import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	public PersonRepository personRepository;
	private final ArrayList<Person> persons;
	List<Person> list;
	List<String[]> data;

	public PersonServiceImpl() {
		persons = new ArrayList<>();
		list = new ArrayList<>();
		data = new ArrayList<>();

	}

	@Override
	public Person save(Person person) {

		person.setCreatedOn(formatDate(new Date()));
		person.setUpdatedOn(formatDate(new Date()));

		return personRepository.save(person);
	}

	@Override
	public String delete(String id) {
		Person person = getPersonDetails(id);

		personRepository.delete(person);
		return "person is deleted" + id;
	}

	@Override
	public void updatePersonDetails(Person person, String id) {
		Person personObj = getPersonDetails(id);
		personObj.setFirstName(person.getFirstName());
		personObj.setLastName(person.getLastName());
		personObj.setFirstName(person.getFirstName());
		personObj.setEmail(person.getEmail());
		personObj.setUserName(person.getUserName());
		personObj.setPassword(person.getPassword());
		personObj.setFirstName(person.getFirstName());
		personObj.setJoiningDate(person.getJoiningDate());
		personObj.setUpdatedOn(formatDate(new Date()));
		personObj.setAddress(person.getAddress());
		personRepository.save(personObj);

	}

	/*
	 * @Override public Person getPersonDetails(String id) { Optional<Person>
	 * personObj = personRepository.findById(id); Person person = personObj.get();
	 * 
	 * return person; }
	 */
	/*
	 * @Override public Optional<Person> getPersonDetails(String id) {
	 * Optional<Person> personObj = personRepository.findById(id);
	 * 
	 * 
	 * return personObj; }
	 */
	public Person getPersonDetails(String id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			throw new PersonNotFoundException("Resource With Id::" + id + " is not found");
		}
		Person resource = person.get();
		return resource;
	}

	public String formatDate(Date date) {

		SimpleDateFormat formDate = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		String str = formDate.format(date);
		return str;

	}

	@Override
	public boolean findByEmail(String email) {
		List<Person> list = personRepository.findAll();
		for (Person person : list) {
			if (person.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean findByUserName(String userName) {

		List<Person> list = personRepository.findAll();
		for (Person person : list) {
			if (person.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("resource")
	public List<Person> readFromCsv() {

		FileInputStream fis = null;

		try {

			String fileName = "E:/csv/person.csv";

			fis = new FileInputStream(new File(fileName));
			CSVReader reader = new CSVReader(new InputStreamReader(fis));
			String[] nextLine;
			reader.readNext();

			while ((nextLine = reader.readNext()) != null) {

				Person person = new Person(nextLine[0], (nextLine[1]), nextLine[2], nextLine[3], nextLine[4],
						parseDate(nextLine[5]),
						new ArrayList<>(Arrays.asList(stringToObject(nextLine[6]), stringToObject(nextLine[7]))));

				person.setCreatedOn(formatDate(new Date()));
				person.setUpdatedOn(formatDate(new Date()));
				persons.add(person);
				for (Person person1 : persons) {
					save(person1);
				}
				list = personRepository.findAll();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	@SuppressWarnings("resource")
	public List<String[]> writeToCsv() {

		try {

			String fileName = "E:/csv/export1.csv";

			FileWriter writer = new FileWriter(fileName);

			CSVWriter csvWriter = new CSVWriter(writer);

			List<Person> persons = personRepository.findAll();
			List<String[]> data = toStringArray(persons);
			csvWriter.writeAll(data);

			csvWriter.close();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return data;
	}

	public List<String[]> toStringArray(List<Person> persons) {
		List<String[]> records = new ArrayList<>();

		// adding header record
		records.add(new String[] { "FNAME", "LName", "EMAIL", "USERNAME", "PASSWORD", "JOININGDATE","LOCAL ADDRESS","PERMANANT ADDRESS"});
		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			Person person = it.next();
			Address local= getLocalAddressDetails(person.getAddress());
			Address permanant = getPermanantAddressDetails(person.getAddress());
			records.add(new String[] { person.getFirstName(), person.getLastName(), person.getEmail(),
					person.getUserName(), person.getPassword(), formatDate(person.getJoiningDate()),ObjectToString(local),ObjectToString(permanant) });
		}
		System.out.println(records);
		return records;

	}
	 public Address getLocalAddressDetails(List<Address> address) {
		 Iterator<Address> it = address.iterator();
			
				Address local = it.next();
			
			return local;
	 }
	 public Address getPermanantAddressDetails(List<Address> address) {
		 Iterator<Address> it = address.iterator();
		 Address permanant = null;
			while(it.hasNext()) {
				 permanant = it.next();
			}
			return permanant;
	 }
	public String ObjectToString(Address address) {
		
		
		 return (address.getAddressType()+","+address.getStreetAddress()+","+address.getCity()+","+address.getState()+","+address.getCountry()+","+String.valueOf(address.getZip()));
		 
		}
		
		
		
	

	/*
	 * 
	 * 
	 * public ArrayList<Employee> findAllEmployees() {
	 * 
	 * FileInputStream fis = null;
	 * 
	 * try {
	 * 
	 * // String fileName = "src/main/resources/person.csv"; fis = new
	 * FileInputStream(new File(fileName)); CSVReader reader = new CSVReader(new
	 * InputStreamReader(fis)); String[] nextLine; reader.readNext();
	 * 
	 * while ((nextLine = reader.readNext()) != null) {
	 * 
	 * Employee emp = new Employee(nextLine[0], (nextLine[1])); employees.add(emp);
	 * }
	 * 
	 * } catch (FileNotFoundException ex) {
	 * Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null,
	 * ex); } catch (IOException ex) {
	 * Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null,
	 * ex); } finally { try { if (fis != null) { fis.close(); } } catch (IOException
	 * ex) {
	 * Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE,null,
	 * ex); } }
	 * 
	 * return employees; } // C
	 */
	public Date parseDate(String date) {
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");

		// Converting the String back to java.util.Date

		try {
			Date date1 = sm.parse(date);
			return date1;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	public Address stringToObject(String str) {
		List<String> list = Arrays.asList(str.split("\\,"));

		Address add = new Address();
		add.setAddressType(list.get(0));
		add.setStreetAddress(list.get(1));
		add.setCity(list.get(2));
		add.setState(list.get(3));
		add.setCountry(list.get(4));
		add.setZip(Integer.valueOf(list.get(5)));
		return add;
	}

}
