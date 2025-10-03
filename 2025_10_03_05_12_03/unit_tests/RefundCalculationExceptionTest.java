package com.example.airtransport.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for RefundCalculationException.
 * Covers normal and edge cases for exception instantiation and message retrieval.
 */
public class RefundCalculationExceptionTest {

    /**
     * Test exception instantiation with a normal message.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Refund calculation failed due to invalid fare rules.";
        RefundCalculationException ex = new RefundCalculationException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception instantiation with a null message (edge case).
     */
    @Test
    void testExceptionWithNullMessage() {
        RefundCalculationException ex = new RefundCalculationException(null);
        assertNull(ex.getMessage());
    }
}
