package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment model.
 */
class PaymentTest {

    @Test
    @DisplayName("Test getters and setters for Payment")
    void testGettersAndSetters() {
        Payment payment = new Payment();
        payment.setPaymentId("P123");
        payment.setMethod("CARD");
        payment.setAmount(100.0);
        payment.setStatus("SUCCESS");

        assertEquals("P123", payment.getPaymentId());
        assertEquals("CARD", payment.getMethod());
        assertEquals(100.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    @DisplayName("Test Payment with null fields and zero amount")
    void testNullFields() {
        Payment payment = new Payment();
        assertNull(payment.getPaymentId());
        assertNull(payment.getMethod());
        assertEquals(0.0, payment.getAmount());
        assertNull(payment.getStatus());
    }
}
