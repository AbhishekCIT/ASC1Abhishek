package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidInputException.
 */
class InvalidInputExceptionTest {

    /**
     * Test normal scenario: constructor with message.
     */
    @Test
    @DisplayName("Constructor with message sets message correctly")
    void testConstructorWithMessage() {
        String msg = "Invalid input";
        InvalidInputException ex = new InvalidInputException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test edge case: null message.
     */
    @Test
    @DisplayName("Constructor with null message")
    void testConstructorWithNullMessage() {
        InvalidInputException ex = new InvalidInputException(null);
        assertNull(ex.getMessage());
    }
}
