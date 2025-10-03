package com.example.airtransport.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentFailedException.
 * Covers normal and edge cases for exception instantiation and message retrieval.
 */
public class PaymentFailedExceptionTest {

    /**
     * Test exception instantiation with a normal message.
     */
    @Test
    void testExceptionWithMessage() {
        String message = "Payment failed due to insufficient funds.";
        PaymentFailedException ex = new PaymentFailedException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception instantiation with a null message (edge case).
     */
    @Test
    void testExceptionWithNullMessage() {
        PaymentFailedException ex = new PaymentFailedException(null);
        assertNull(ex.getMessage());
    }
}
