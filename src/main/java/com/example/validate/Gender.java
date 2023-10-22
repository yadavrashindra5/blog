package com.example.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
    String message() default "Invalid gender. Accepted values are 'male' or 'female'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
