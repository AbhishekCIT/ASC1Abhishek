package com.airtransport.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentFailedException.
 */
public class PaymentFailedExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String message = "Payment failed";
        PaymentFailedException ex = new PaymentFailedException(message);
        assertEquals(message, ex.getMessage());
    }

    /**
     * Test exception is instance of RuntimeException.
     */
    @Test
    void testExceptionInheritance() {
        PaymentFailedException ex = new PaymentFailedException("msg");
        assertTrue(ex instanceof RuntimeException);
    }
}
