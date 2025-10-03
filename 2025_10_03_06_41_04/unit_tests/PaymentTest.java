package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity.
 */
public class PaymentTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testFields() {
        Payment payment = new Payment();
        Booking booking = new Booking();
        payment.setId(1L);
        payment.setBooking(booking);
        payment.setAmount(100.0);
        payment.setPaymentStatus("SUCCESS");
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);
        payment.setPaymentMethod("CARD");

        assertEquals(1L, payment.getId());
        assertEquals(booking, payment.getBooking());
        assertEquals(100.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getPaymentStatus());
        assertEquals(now, payment.getPaymentDate());
        assertEquals("CARD", payment.getPaymentMethod());
    }

    /**
     * Test edge case: negative amount.
     */
    @Test
    void testNegativeAmount() {
        Payment payment = new Payment();
        payment.setAmount(-50.0);
        assertEquals(-50.0, payment.getAmount());
    }
}
