package com.ex.initproj.validations;

import com.ex.initproj.validations.validator.ValidPhoneNumberLengthVld;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPhoneNumberLengthVld.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumberLength {
    String message() default "The phone number must only have 10 numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
