package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ErrorResponse model.
 * Covers constructor, getter, setter, and edge cases.
 */
class ErrorResponseTest {
    @Test
    void testConstructorAndGetter() {
        // Purpose: Test constructor and getter
        ErrorResponse errorResponse = new ErrorResponse("Some error");
        assertEquals("Some error", errorResponse.getError());
    }

    @Test
    void testSetter() {
        // Purpose: Test setter and null value
        ErrorResponse errorResponse = new ErrorResponse("Initial");
        errorResponse.setError("Updated");
        assertEquals("Updated", errorResponse.getError());
        errorResponse.setError(null);
        assertNull(errorResponse.getError());
    }

    @Test
    void testNullConstructorArg() {
        // Purpose: Test constructor with null argument
        ErrorResponse errorResponse = new ErrorResponse(null);
        assertNull(errorResponse.getError());
    }
}
