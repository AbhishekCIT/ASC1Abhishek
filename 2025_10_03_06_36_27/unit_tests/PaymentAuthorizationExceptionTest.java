package com.example.airbooking.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentAuthorizationException
 */
public class PaymentAuthorizationExceptionTest {
    /**
     * Test exception message is set correctly
     */
    @Test
    void testMessage() {
        PaymentAuthorizationException ex = new PaymentAuthorizationException("Payment not authorized");
        assertEquals("Payment not authorized", ex.getMessage());
    }

    /**
     * Test exception with null message
     */
    @Test
    void testNullMessage() {
        PaymentAuthorizationException ex = new PaymentAuthorizationException(null);
        assertNull(ex.getMessage());
    }
}
