package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NoFlightsFoundException.
 */
class NoFlightsFoundExceptionTest {

    /**
     * Test normal scenario: constructor with message.
     */
    @Test
    @DisplayName("Constructor with message sets message correctly")
    void testConstructorWithMessage() {
        String msg = "No flights found";
        NoFlightsFoundException ex = new NoFlightsFoundException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test edge case: null message.
     */
    @Test
    @DisplayName("Constructor with null message")
    void testConstructorWithNullMessage() {
        NoFlightsFoundException ex = new NoFlightsFoundException(null);
        assertNull(ex.getMessage());
    }
}
