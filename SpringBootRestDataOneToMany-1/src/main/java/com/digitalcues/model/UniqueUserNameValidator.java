package com.digitalcues.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitalcues.service.PersonService;

public class UniqueUserNameValidator  implements ConstraintValidator<UniqueUser, String> {
	@Autowired
	private PersonService personservice;
	
	public UniqueUserNameValidator(PersonService personservice) {
        this.personservice = personservice;
    }
  
	
    public void initialize(UniqueUser constraint) {
    }
     @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
    	System.out.println(userName);
		if(userName != null && personservice.findByUserName(userName
				)) {
			return false;
		}
		return true;
    }
 

}
