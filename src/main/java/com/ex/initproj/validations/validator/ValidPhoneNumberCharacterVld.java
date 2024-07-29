package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidPhoneNumberCharacter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidPhoneNumberCharacterVld implements ConstraintValidator<ValidPhoneNumberCharacter, String> {
    @Override
    public void initialize(ValidPhoneNumberCharacter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
//        for (int i = 0; i < str.length(); i++){
//            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
//                return false;
//            }
//        }
//        return false;
        String regex = "^[0-9]$";
        return str.matches(regex);
    }
}
