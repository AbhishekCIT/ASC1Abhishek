package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidPassengerCountException.
 */
class InvalidPassengerCountExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    @DisplayName("Constructor sets message correctly")
    void testConstructor_Message() {
        String msg = "Invalid passenger count";
        InvalidPassengerCountException ex = new InvalidPassengerCountException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test that the exception is a RuntimeException.
     */
    @Test
    @DisplayName("Exception is instance of RuntimeException")
    void testIsRuntimeException() {
        InvalidPassengerCountException ex = new InvalidPassengerCountException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
