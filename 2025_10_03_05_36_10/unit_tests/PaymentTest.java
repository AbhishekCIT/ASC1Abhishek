package com.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Payment model.
 * Covers all getters and setters, including edge and boundary cases.
 */
public class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    /**
     * Test setting and getting paymentId.
     */
    @Test
    void testPaymentId() {
        payment.setPaymentId("P123");
        assertEquals("P123", payment.getPaymentId());
        payment.setPaymentId(null);
        assertNull(payment.getPaymentId());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    void testStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        payment.setStatus(null);
        assertNull(payment.getStatus());
    }

    /**
     * Test setting and getting amount.
     * Purpose: Normal, zero, negative, and null cases.
     */
    @Test
    void testAmount() {
        payment.setAmount(100.0);
        assertEquals(100.0, payment.getAmount());
        payment.setAmount(0.0);
        assertEquals(0.0, payment.getAmount());
        payment.setAmount(-10.0);
        assertEquals(-10.0, payment.getAmount());
        payment.setAmount(null);
        assertNull(payment.getAmount());
    }

    /**
     * Test setting and getting paymentDate.
     */
    @Test
    void testPaymentDate() {
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);
        assertEquals(now, payment.getPaymentDate());
        payment.setPaymentDate(null);
        assertNull(payment.getPaymentDate());
    }

    /**
     * Test setting and getting booking.
     */
    @Test
    void testBooking() {
        Booking booking = new Booking();
        booking.setBookingId("B123");
        payment.setBooking(booking);
        assertEquals(booking, payment.getBooking());
        payment.setBooking(null);
        assertNull(payment.getBooking());
    }
}
