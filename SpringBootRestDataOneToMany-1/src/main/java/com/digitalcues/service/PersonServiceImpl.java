package com.digitalcues.service;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digitalcues.exception.PersonNotFoundException;
import com.digitalcues.model.Address;

import com.digitalcues.model.Person;
import com.digitalcues.repository.PersonRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	public PersonRepository personRepository;

	Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	public Person save(Person person) {
		log.info("in save method of personServiceImpl");
		person.setCreatedOn(formatDate(new Date()));
		person.setUpdatedOn(formatDate(new Date()));

		return personRepository.save(person);
	}

	@Override
	public String delete(String id) {
		log.info("in delete method of personServiceImpl");
		Person person = getPersonDetails(id);

		personRepository.delete(person);
		return "person is deleted" + id;
	}

	@Override
	public void updatePersonDetails(Person person, String id) {
		log.info("in update method of personServiceImpl");
		Person personObj = getPersonDetails(id);
		personObj.setPersonId(person.getPersonId());
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

	public Person getPersonDetails(String id) {
		log.info("in  getPersonDetails method of personServiceImpl");
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent()) {
			throw new PersonNotFoundException("Resource With Id::" + id + " is not found");
		}
		return person.get();

	}

	public String formatDate(Date date) {
		log.info("in formatDate  method of personServiceImpl");
		SimpleDateFormat formDate = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		return formDate.format(date);
		
	}

	@Override
	public boolean findByEmail(String email) {
		log.info("in findByEmail  method of personServiceImpl");
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

		log.info("in findByUserName  method of personServiceImpl");
		List<Person> list = personRepository.findAll();
		for (Person person : list) {
			if (person.getUserName().equals(userName)) {
				return true;
			}
		}
		return false;
	}

	public List<Person> readFromCsv(String fileName) {

		List<Person> list = new ArrayList<>();
		List<Person> persons = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(fileName);
				CSVReader reader = new CSVReader(new InputStreamReader(fis))) {

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
			log.error("FileNotFoundexception", ex);

		} catch (IOException ex) {
			log.error("Ioexception in readFromCsv method", ex);
		}

		return list;
	}

	
	public List<String[]> writeToCsv() {
		List<String[]> data = new ArrayList<>();
		String fileName = "E:/csv/export1.csv";

		try (FileWriter writer = new FileWriter(fileName); CSVWriter csvWriter = new CSVWriter(writer)) {

			List<Person> persons = personRepository.findAll();
			data = toStringArray(persons);
			csvWriter.writeAll(data);

			return data;

		} catch (FileNotFoundException ex) {
		
			log.error("file not found exception", ex);
		} catch (IOException ex) {
			log.error("Ioexception", ex);
		}

		return data;
	}

	public List<String[]> toStringArray(List<Person> persons) {
		List<String[]> records = new ArrayList<>();

		// adding header record
		records.add(new String[] { "FNAME", "LName", "EMAIL", "USERNAME", "PASSWORD", "JOININGDATE", "LOCAL ADDRESS",
				"PERMANANT ADDRESS" });
		Iterator<Person> it = persons.iterator();
		while (it.hasNext()) {
			Person person = it.next();
			Address local = getLocalAddressDetails(person.getAddress());
			Address permanant = getPermanantAddressDetails(person.getAddress());
			records.add(new String[] { person.getFirstName(), person.getLastName(), person.getEmail(),
					person.getUserName(), person.getPassword(), formatDate(person.getJoiningDate()),
					objectToString(local), objectToString(permanant) });
		}

		return records;

	}

	public Address getLocalAddressDetails(List<Address> address) {
		Iterator<Address> it = address.iterator();

		return it.next();

	}

	public Address getPermanantAddressDetails(List<Address> address) {
		Iterator<Address> it = address.iterator();
		Address permanant = null;
		while (it.hasNext()) {
			permanant = it.next();
		}
		return permanant;
	}

	public String objectToString(Address address) {

		return (address.getAddressType() + "," + address.getStreetAddress() + "," + address.getCity() + ","
				+ address.getState() + "," + address.getCountry() + "," + address.getZip());

	}

	public Date parseDate(String date) {
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");

		// Converting the String back to java.util.Date

		try {
			return sm.parse(date);

		} catch (ParseException ex) {

			log.error("paeseException", ex);
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
