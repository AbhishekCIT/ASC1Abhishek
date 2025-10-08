package com.example.calculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ErrorResponse.
 * Purpose: Test constructors, getters, and setters for normal and edge cases.
 */
public class ErrorResponseTest {
    /**
     * Test default constructor and setter.
     */
    @Test
    void testDefaultConstructorAndSetter() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError("Some error");
        assertEquals("Some error", errorResponse.getError());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        ErrorResponse errorResponse = new ErrorResponse("Division by zero");
        assertEquals("Division by zero", errorResponse.getError());
    }

    /**
     * Test edge case: null value.
     */
    @Test
    void testNullValue() {
        ErrorResponse errorResponse = new ErrorResponse(null);
        assertNull(errorResponse.getError());
    }
}
