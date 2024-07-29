package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidPasswordCharacter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
@Slf4j
public class ValidPasswordCharacterVld implements ConstraintValidator<ValidPasswordCharacter, String> {
    @Override
    public void initialize(ValidPasswordCharacter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        String character = "^[a-zA-Z0-9]$";
        return Pattern.compile(character).matcher(str).matches();
    }
}
