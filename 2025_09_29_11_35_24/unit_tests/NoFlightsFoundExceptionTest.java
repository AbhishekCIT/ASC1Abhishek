package com.airtransport.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NoFlightsFoundException.
 */
public class NoFlightsFoundExceptionTest {
    @Test
    @DisplayName("Test constructor with message")
    void testConstructorWithMessage() {
        NoFlightsFoundException ex = new NoFlightsFoundException("No flights found");
        assertEquals("No flights found", ex.getMessage());
    }

    @Test
    @DisplayName("Test constructor with null message (edge case)")
    void testConstructorWithNullMessage() {
        NoFlightsFoundException ex = new NoFlightsFoundException(null);
        assertNull(ex.getMessage());
    }
}
