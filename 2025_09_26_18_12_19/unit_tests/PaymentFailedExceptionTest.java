package com.example.flightbooking.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentFailedException.
 */
class PaymentFailedExceptionTest {

    /**
     * Test that the exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Payment processing failed.";
        PaymentFailedException ex = new PaymentFailedException(message);
        assertEquals(message, ex.getMessage(), "Exception message should match input");
    }

    /**
     * Test that PaymentFailedException is a RuntimeException.
     */
    @Test
    void testExceptionType() {
        PaymentFailedException ex = new PaymentFailedException("msg");
        assertTrue(ex instanceof RuntimeException, "Should be a RuntimeException");
    }
}
