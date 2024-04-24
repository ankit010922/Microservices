package com.dish.roles.annotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccessTypeValidatorClass  implements ConstraintValidator<AccessTypeValidator, String> {

    private final Set<String> validValues = new HashSet<>(Arrays.asList("READ", "WRITE", "CREATE", "DELETE", "ALL"));

    @Override
    public void initialize(AccessTypeValidator constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validValues.contains(value.toUpperCase());
    }
}