package com.ex.initproj.validations;

import com.ex.initproj.validations.validator.ValidPasswordLengthVld;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPasswordLengthVld.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPasswordLength {
    String message() default "Password only have 3 - 5 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
