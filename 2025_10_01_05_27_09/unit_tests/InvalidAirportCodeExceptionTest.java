package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidAirportCodeException.
 */
class InvalidAirportCodeExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    @DisplayName("Constructor sets message correctly")
    void testConstructor_Message() {
        String msg = "Invalid code";
        InvalidAirportCodeException ex = new InvalidAirportCodeException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test that the exception is a RuntimeException.
     */
    @Test
    @DisplayName("Exception is instance of RuntimeException")
    void testIsRuntimeException() {
        InvalidAirportCodeException ex = new InvalidAirportCodeException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
