package com.digitalcues.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitalcues.repository.PersonRepository;
import com.digitalcues.service.PersonService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	@Autowired
	private PersonService personservice;
	
	public UniqueEmailValidator(PersonService personservice) {
        this.personservice = personservice;
    }
  
	@Override
    public void initialize(UniqueEmail constraint) {
    }
     @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
    	System.out.println(email);
		if(email != null && personservice.findByEmail(email)) {
			return false;
		}
		return true;
    }
 
}

