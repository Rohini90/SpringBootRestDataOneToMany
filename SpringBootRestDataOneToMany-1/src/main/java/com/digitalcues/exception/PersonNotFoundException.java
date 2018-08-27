package com.digitalcues.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(HttpStatus.OK)
public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String exception) {
	    super(exception);
	  }

}
