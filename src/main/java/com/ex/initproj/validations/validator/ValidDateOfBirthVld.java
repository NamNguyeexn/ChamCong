package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidDateOfBirth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Period;

public class ValidDateOfBirthVld implements ConstraintValidator<ValidDateOfBirth, Date> {
    @Override
    public void initialize(ValidDateOfBirth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date dob, ConstraintValidatorContext constraintValidatorContext) {
        return Period.between(dob.toLocalDate(), LocalDateTime.now().toLocalDate()).getYears() >= 18;
    }
}
