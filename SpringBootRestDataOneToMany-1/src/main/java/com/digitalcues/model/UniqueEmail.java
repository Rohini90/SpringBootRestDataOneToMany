package com.digitalcues.model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
public @interface UniqueEmail {
	public String message() default"there is already user with this email";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
