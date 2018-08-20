package com.digitalcues.model;

import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="address")
public class Address {
	
	@Id
	private String id;
	
	private String addressType;
	
	private String streetAddress;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private int zip;
	
	@ManyToOne
	
	private Person person;

	public Address(String addressType, String streetAddress, String city, String state, String country, int zip,
			Person person) {
		super();
		this.addressType = addressType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.person = person;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", streetAddress=" + streetAddress + ", city="
				+ city + ", state=" + state + ", country=" + country + ", zip=" + zip + ", person=" + person + "]";
	}
	
	
	
	
	

}
