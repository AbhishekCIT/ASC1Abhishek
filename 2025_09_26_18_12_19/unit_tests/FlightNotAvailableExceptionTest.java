package com.example.flightbooking.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightNotAvailableException.
 */
class FlightNotAvailableExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Flight not available.";
        FlightNotAvailableException ex = new FlightNotAvailableException(message);
        assertEquals(message, ex.getMessage(), "Exception message should match input");
    }

    /**
     * Test that FlightNotAvailableException is a RuntimeException.
     */
    @Test
    void testExceptionType() {
        FlightNotAvailableException ex = new FlightNotAvailableException("msg");
        assertTrue(ex instanceof RuntimeException, "Should be a RuntimeException");
    }
}
