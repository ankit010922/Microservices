package com.dish.roles.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccessTypeValidatorClass.class)
public @interface AccessTypeValidator {
	 String message() default "Invalid access type";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
