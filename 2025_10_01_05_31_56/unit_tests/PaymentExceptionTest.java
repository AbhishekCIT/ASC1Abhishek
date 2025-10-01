package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentException.
 * Covers normal and edge cases for exception construction and message.
 */
class PaymentExceptionTest {

    /**
     * Test that PaymentException stores and returns the correct message.
     */
    @Test
    void testPaymentException_Message() {
        String msg = "Payment error occurred";
        PaymentException ex = new PaymentException(msg);
        assertEquals(msg, ex.getMessage());
    }

    /**
     * Test PaymentException with null message (edge case).
     */
    @Test
    void testPaymentException_NullMessage() {
        PaymentException ex = new PaymentException(null);
        assertNull(ex.getMessage());
    }
}
