package com.example.SomayaAssaadiExam.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

public class AdminTest {
    @Autowired
    private LocalValidatorFactoryBean validator;


    @Test
    public void shouldThrowConstraintsValidationWithEmailIsBlank(){
      Admin  admin = new Admin(null, "","somaua",null);
        Set<ConstraintViolation<Admin>> violations = validator.validate(admin);
        assertFalse(violations.isEmpty());
        boolean found = false;
        for (ConstraintViolation<Admin> violation : violations)
            if (violation.getMessage().equals("Email is required")) {
                found = true;
                break;
            }
        assertTrue(found);
    }
}
