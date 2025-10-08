package com.example.airbooking.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightNotFoundException.
 * Covers normal and edge scenarios for exception message.
 */
class FlightNotFoundExceptionTest {
    /**
     * Test FlightNotFoundException with a valid message (normal scenario).
     */
    @Test
    void testFlightNotFoundException_validMessage() {
        FlightNotFoundException ex = new FlightNotFoundException("No flights found");
        assertEquals("No flights found", ex.getMessage());
    }

    /**
     * Test FlightNotFoundException with null message (edge case).
     */
    @Test
    void testFlightNotFoundException_nullMessage() {
        FlightNotFoundException ex = new FlightNotFoundException(null);
        assertNull(ex.getMessage());
    }
}
