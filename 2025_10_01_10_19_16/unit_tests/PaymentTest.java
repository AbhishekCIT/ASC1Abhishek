package com.airtransport.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity.
 */
class PaymentTest {

    /**
     * Test getters and setters for Payment.
     */
    @Test
    void testPaymentGettersSetters() {
        Payment payment = new Payment();
        payment.setPaymentId("P123");
        payment.setBookingId("B456");
        payment.setAmount(350.0);
        payment.setStatus("SUCCESS");
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);
        payment.setTransactionId("TX789");

        assertEquals("P123", payment.getPaymentId());
        assertEquals("B456", payment.getBookingId());
        assertEquals(350.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(now, payment.getPaymentDate());
        assertEquals("TX789", payment.getTransactionId());
    }
}
