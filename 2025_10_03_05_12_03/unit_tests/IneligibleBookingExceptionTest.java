package com.example.airtransport.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for IneligibleBookingException.
 * Covers normal and edge cases for exception instantiation and message retrieval.
 */
public class IneligibleBookingExceptionTest {

    /**
     * Test exception instantiation with a normal message.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Booking is not eligible for cancellation.";
        IneligibleBookingException ex = new IneligibleBookingException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception instantiation with a null message (edge case).
     */
    @Test
    void testExceptionWithNullMessage() {
        IneligibleBookingException ex = new IneligibleBookingException(null);
        assertNull(ex.getMessage());
    }
}
