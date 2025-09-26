package com.example.flightbooking.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ErrorResponse model.
 */
class ErrorResponseTest {
    @Test
    @DisplayName("Should set error via constructor and get it correctly")
    void testConstructorAndGetter() {
        ErrorResponse response = new ErrorResponse("Some error");
        assertEquals("Some error", response.getError(), "Error should be set via constructor and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get error via setter")
    void testSetter() {
        ErrorResponse response = new ErrorResponse(null);
        response.setError("Another error");
        assertEquals("Another error", response.getError(), "Error should be set and retrieved via setter");
    }

    @Test
    @DisplayName("Should handle null value for error")
    void testNullError() {
        ErrorResponse response = new ErrorResponse(null);
        assertNull(response.getError(), "Error should be null when set to null");
        response.setError(null);
        assertNull(response.getError(), "Error should remain null after setter");
    }
}
