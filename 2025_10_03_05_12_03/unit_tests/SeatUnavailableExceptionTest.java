package com.example.airtransport.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for SeatUnavailableException.
 * Covers normal and edge cases for exception instantiation and message retrieval.
 */
public class SeatUnavailableExceptionTest {

    /**
     * Test exception instantiation with a normal message.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Selected seat is unavailable.";
        SeatUnavailableException ex = new SeatUnavailableException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception instantiation with a null message (edge case).
     */
    @Test
    void testExceptionWithNullMessage() {
        SeatUnavailableException ex = new SeatUnavailableException(null);
        assertNull(ex.getMessage());
    }
}
