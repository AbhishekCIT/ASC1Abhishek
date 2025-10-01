package com.example.flightsearch.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NoFlightsFoundException.
 */
class NoFlightsFoundExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    @DisplayName("Constructor sets message correctly")
    void testConstructor_Message() {
        String msg = "No flights found";
        NoFlightsFoundException ex = new NoFlightsFoundException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test that the exception is a RuntimeException.
     */
    @Test
    @DisplayName("Exception is instance of RuntimeException")
    void testIsRuntimeException() {
        NoFlightsFoundException ex = new NoFlightsFoundException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
