package com.mohanad.springpractice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {
    private String val;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        val = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return val != null && val.startsWith("01");
    }
}
