package com.ex.initproj.validations;

import com.ex.initproj.validations.validator.ValidUsernameLengthVld;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidUsernameLengthVld.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsernameLength {
    String message() default "Username only have 3 - 5 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
