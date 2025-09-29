package com.example.airtransport.exception;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentProcessingException.
 * Covers constructor and message propagation.
 */
class PaymentProcessingExceptionTest {
    @Test
    void constructor_setsMessageCorrectly() {
        // Purpose: Ensure message is set and retrievable
        String msg = "Payment failed";
        PaymentProcessingException ex = new PaymentProcessingException(msg);
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void constructor_nullMessage_allowed() {
        // Purpose: Allow null message
        PaymentProcessingException ex = new PaymentProcessingException(null);
        assertNull(ex.getMessage());
    }
}
