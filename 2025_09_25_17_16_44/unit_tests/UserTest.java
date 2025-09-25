package com.example.scheduledreporting.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for User entity validation.
 * Covers normal, edge, and error cases for entity fields.
 */
class UserTest {
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Valid User passes validation")
    void testValidUser() {
        User user = buildValidUser();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty(), "Expected no validation errors for valid user");
    }

    @Test
    @DisplayName("Blank username triggers validation error")
    void testBlankUsername() {
        User user = buildValidUser();
        user.setUsername("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    @DisplayName("Blank email triggers validation error")
    void testBlankEmail() {
        User user = buildValidUser();
        user.setEmail("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    @DisplayName("Invalid email triggers validation error")
    void testInvalidEmail() {
        User user = buildValidUser();
        user.setEmail("not-an-email");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    @DisplayName("Blank role triggers validation error")
    void testBlankRole() {
        User user = buildValidUser();
        user.setRole("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("role")));
    }

    // Helper to build a valid User
    private User buildValidUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setEmail("user1@example.com");
        user.setRole("USER");
        return user;
    }
}
