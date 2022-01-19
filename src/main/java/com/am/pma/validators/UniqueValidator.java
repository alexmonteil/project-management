package com.am.pma.validators;

import com.am.pma.entities.Employee;
import com.am.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeService employeeService;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Employee targetEmployee = employeeService.findByEmail(value);
        return targetEmployee == null;
    }
}
