package com.ex.initproj.validations;

import com.ex.initproj.validations.validator.ValidCheckOutTimeVld;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidCheckOutTimeVld.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCheckOutTime {
    String message() default "Cant Check In And Out At The Same Time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
