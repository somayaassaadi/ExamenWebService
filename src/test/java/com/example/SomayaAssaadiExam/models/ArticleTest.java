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
public class ArticleTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Test
    public void shouldSuccessWithValidNameAndValidDescription(){
        Article article = new Article(null, "name","valid description",12,1);
        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldThrowConstraintsValidationWithNameIsBlank(){
        Article article = new Article(null, "","valid description",12,1);
        Set<ConstraintViolation<Article>> violations = validator.validate(article);
        assertFalse(violations.isEmpty());
        boolean found = false;
        for (ConstraintViolation<Article> violation : violations)
            if (violation.getMessage().equals("NAME is required")) {
                found = true;
                break;
            }
        assertTrue(found);
    }
}
