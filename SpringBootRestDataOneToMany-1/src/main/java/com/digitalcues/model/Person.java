package com.digitalcues.model;



import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="person")
public class Person {
	
	@Id
	private ObjectId personId;
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	
	@Email
	private String email;
	
	@Size(min=6,message="Username must be at least 6 char length ")
	
	private String userName;
	
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",message="password must be Minimum eight characters, at least one letter, one number and one special character:")
	private String password;
	
	private Date joiningDate;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	@OneToMany(mappedBy="person", cascade=CascadeType.ALL )
	private List<Address> address;
	
	
	





	public Person(String firstName, String lastName, @Email(message = "Email Id already exists") String email,
			@Size(min = 6, message = "Username must be at least 6 char length ") String userName,
			@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "password must be Minimum eight characters, at least one letter, one number and one special character:") String password,
			Date joiningDate, List<Address> address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.joiningDate = joiningDate;
		this.address = address;
	}


	public ObjectId getPersonId() {
		return personId;
	}


	public void setPersonId(ObjectId personId) {
		this.personId = personId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getJoiningDate() {
		return joiningDate;
	}


	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}


	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", userName=" + userName + ", password=" + password + ", joiningDate=" + joiningDate
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", address=" + address + "]";
	}
	
	
	
	
	

}
