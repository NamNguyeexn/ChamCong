package com.ex.initproj.validations.validator;

import com.ex.initproj.validations.ValidCheckOutTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class ValidCheckOutTimeVld implements ConstraintValidator<ValidCheckOutTime, LocalDateTime> {
    @Override
    public void initialize(ValidCheckOutTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
//        if (localDateTime.isBefore(LocalDateTime.now().plusHours(8))){
//            log.info("ValidCheckOutTimeVld - isValid - not enough 8 hour");
//            return false;
//        }
        if (localDateTime.toLocalDate().isEqual(LocalDateTime.now().toLocalDate())
                && localDateTime.isBefore(LocalDateTime.now())){
            log.info("ValidCheckOutTimeVld - isValid - same day");
        }
        return true;
    }
}
