package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidPhoneNumberLength;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidPhoneNumberLengthVld implements ConstraintValidator<ValidPhoneNumberLength, String> {
    @Override
    public void initialize(ValidPhoneNumberLength constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return str.length() == 10;
    }
}
