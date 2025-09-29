package com.example.booking.exception;

import org.junit.jupiter.api.*;

/**
 * Unit tests for PaymentFailedException.
 */
class PaymentFailedExceptionTest {
    /**
     * Test exception message is set correctly.
     */
    @Test
    void testExceptionMessage() {
        String msg = "Payment failed";
        PaymentFailedException ex = new PaymentFailedException(msg);
        Assertions.assertEquals(msg, ex.getMessage());
    }

    /**
     * Test exception with null message (edge case).
     */
    @Test
    void testExceptionNullMessage() {
        PaymentFailedException ex = new PaymentFailedException(null);
        Assertions.assertNull(ex.getMessage());
    }
}
