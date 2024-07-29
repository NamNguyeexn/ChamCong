package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidUsernameLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidUsernameLengthVld implements ConstraintValidator<ValidUsernameLength, String> {
    @Override
    public void initialize(ValidUsernameLength constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return str.length() >= 3 && str.length() <= 5;
    }
}
