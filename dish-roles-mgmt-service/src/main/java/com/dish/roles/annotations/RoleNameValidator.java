package com.dish.roles.annotations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleNameValidator implements ConstraintValidator<ValidRole, String> {

    private static final String SUPER_ADMIN_ROLE = "SUPERADMIN";

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        // Initialization, not needed in this case
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty() && !value.equalsIgnoreCase(SUPER_ADMIN_ROLE);
    }
}