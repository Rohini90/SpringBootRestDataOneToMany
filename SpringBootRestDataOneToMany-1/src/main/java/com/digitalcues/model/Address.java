package com.digitalcues.model;

public class Address {
	
	private String addressType;
	
	private String streetAddress;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private int zip;
	
	
	public Address(String addressType, String streetAddress, String city, String state, String country, int zip
			) {
		super();
		this.addressType = addressType;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		
	}
	
	

	



	public Address() {
		
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

	

	@Override
	public String toString() {
		return "Address [ addressType=" + addressType + ", streetAddress=" + streetAddress + ", city="
				+ city + ", state=" + state + ", country=" + country + ", zip=" + zip +"]";
	}
	
	
	
	
	

}
