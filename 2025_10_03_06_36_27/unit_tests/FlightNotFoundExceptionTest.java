package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightNotFoundException
 */
public class FlightNotFoundExceptionTest {
    /**
     * Test exception message is set correctly
     */
    @Test
    void testMessage() {
        FlightNotFoundException ex = new FlightNotFoundException("Flight not found");
        assertEquals("Flight not found", ex.getMessage());
    }

    /**
     * Test exception with null message
     */
    @Test
    void testNullMessage() {
        FlightNotFoundException ex = new FlightNotFoundException(null);
        assertNull(ex.getMessage());
    }
}
