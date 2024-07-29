package com.ex.initproj.validations;

import com.ex.initproj.validations.validator.ValidDateOfBirthVld;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidDateOfBirthVld.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateOfBirth {
    String message() default "Date ob birth not available";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
