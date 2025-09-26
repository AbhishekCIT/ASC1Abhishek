package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity.
 */
class PaymentTest {
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
    }

    /**
     * Test setting and getting bookingId.
     */
    @Test
    void testBookingId() {
        payment.setBookingId("B456");
        assertEquals("B456", payment.getBookingId());
    }

    /**
     * Test setting and getting amount.
     */
    @Test
    void testAmount() {
        payment.setAmount(100.50);
        assertEquals(100.50, payment.getAmount());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    void testStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    /**
     * Test setting and getting paidAt.
     */
    @Test
    void testPaidAt() {
        LocalDateTime now = LocalDateTime.now();
        payment.setPaidAt(now);
        assertEquals(now, payment.getPaidAt());
    }

    /**
     * Test setting null and edge values.
     */
    @Test
    void testNullAndEdgeValues() {
        payment.setPaymentId(null);
        payment.setBookingId(null);
        payment.setStatus(null);
        payment.setPaidAt(null);
        payment.setAmount(0.0);
        assertNull(payment.getPaymentId());
        assertNull(payment.getBookingId());
        assertNull(payment.getStatus());
        assertNull(payment.getPaidAt());
        assertEquals(0.0, payment.getAmount());
    }
}
