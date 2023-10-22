package com.example.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender,String> {
    @Override
    public void initialize(Gender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        return gender.equalsIgnoreCase("male")||gender.equalsIgnoreCase("female");
    }
}
