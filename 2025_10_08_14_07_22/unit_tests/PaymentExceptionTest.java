package com.example.airbooking.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentException.
 * Covers normal and edge scenarios for exception message.
 */
class PaymentExceptionTest {
    /**
     * Test PaymentException with a valid message (normal scenario).
     */
    @Test
    void testPaymentException_validMessage() {
        PaymentException ex = new PaymentException("Payment failed");
        assertEquals("Payment failed", ex.getMessage());
    }

    /**
     * Test PaymentException with null message (edge case).
     */
    @Test
    void testPaymentException_nullMessage() {
        PaymentException ex = new PaymentException(null);
        assertNull(ex.getMessage());
    }
}
