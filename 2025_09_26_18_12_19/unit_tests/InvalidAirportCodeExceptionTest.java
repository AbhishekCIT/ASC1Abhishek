package com.example.flightbooking.exception;

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
    void testExceptionMessage() {
        String message = "Invalid airport code.";
        InvalidAirportCodeException ex = new InvalidAirportCodeException(message);
        assertEquals(message, ex.getMessage(), "Exception message should match input");
    }

    /**
     * Test that InvalidAirportCodeException is a RuntimeException.
     */
    @Test
    void testExceptionType() {
        InvalidAirportCodeException ex = new InvalidAirportCodeException("msg");
        assertTrue(ex instanceof RuntimeException, "Should be a RuntimeException");
    }
}
