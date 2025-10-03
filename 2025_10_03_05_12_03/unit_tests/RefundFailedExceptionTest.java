package com.example.airtransport.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for RefundFailedException.
 * Covers normal and edge cases for exception instantiation and message retrieval.
 */
public class RefundFailedExceptionTest {

    /**
     * Test exception instantiation with a normal message.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Refund transaction failed.";
        RefundFailedException ex = new RefundFailedException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception instantiation with a null message (edge case).
     */
    @Test
    void testExceptionWithNullMessage() {
        RefundFailedException ex = new RefundFailedException(null);
        assertNull(ex.getMessage());
    }
}
