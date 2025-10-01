package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ErrorResponse model.
 */
class ErrorResponseTest {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse("Initial error");
    }

    /**
     * Test normal scenario: constructor and getter.
     */
    @Test
    @DisplayName("Constructor sets error and getter returns it")
    void testConstructorAndGetter() {
        ErrorResponse er = new ErrorResponse("Some error");
        assertEquals("Some error", er.getError());
    }

    /**
     * Test setter and getter.
     */
    @Test
    @DisplayName("Setter updates error and getter returns updated value")
    void testSetterAndGetter() {
        errorResponse.setError("Updated error");
        assertEquals("Updated error", errorResponse.getError());
    }

    /**
     * Test edge case: set error to null.
     */
    @Test
    @DisplayName("Set error to null")
    void testSetErrorToNull() {
        errorResponse.setError(null);
        assertNull(errorResponse.getError());
    }

    /**
     * Test boundary condition: set error to empty string.
     */
    @Test
    @DisplayName("Set error to empty string")
    void testSetErrorToEmptyString() {
        errorResponse.setError("");
        assertEquals("", errorResponse.getError());
    }
}
