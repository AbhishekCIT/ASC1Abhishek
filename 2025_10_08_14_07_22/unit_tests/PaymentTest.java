package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Payment entity.
 * Covers normal, edge, and boundary scenarios for all fields and methods.
 */
class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    /**
     * Test setting and getting paymentId (normal scenario).
     */
    @Test
    void testPaymentId_setAndGet() {
        payment.setPaymentId(555L);
        assertEquals(555L, payment.getPaymentId());
    }

    /**
     * Test setting and getting booking (normal scenario).
     */
    @Test
    void testBooking_setAndGet() {
        Booking booking = new Booking();
        payment.setBooking(booking);
        assertEquals(booking, payment.getBooking());
    }

    /**
     * Test setting and getting amount (normal scenario).
     */
    @Test
    void testAmount_setAndGet() {
        payment.setAmount(120.50);
        assertEquals(120.50, payment.getAmount());
    }

    /**
     * Test setting and getting status (normal scenario).
     */
    @Test
    void testStatus_setAndGet() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    /**
     * Test setting and getting transactionId (normal scenario).
     */
    @Test
    void testTransactionId_setAndGet() {
        payment.setTransactionId("abc123");
        assertEquals("abc123", payment.getTransactionId());
    }

    /**
     * Test setting and getting processedAt (normal scenario).
     */
    @Test
    void testProcessedAt_setAndGet() {
        LocalDateTime now = LocalDateTime.now();
        payment.setProcessedAt(now);
        assertEquals(now, payment.getProcessedAt());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testSetNullValues() {
        payment.setBooking(null);
        payment.setStatus(null);
        payment.setTransactionId(null);
        payment.setProcessedAt(null);
        assertNull(payment.getBooking());
        assertNull(payment.getStatus());
        assertNull(payment.getTransactionId());
        assertNull(payment.getProcessedAt());
    }

    /**
     * Test boundary condition for amount (edge case).
     */
    @Test
    void testAmount_boundaryValues() {
        payment.setAmount(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, payment.getAmount());
        payment.setAmount(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, payment.getAmount());
        payment.setAmount(Double.NEGATIVE_INFINITY);
        assertEquals(Double.NEGATIVE_INFINITY, payment.getAmount());
    }
}
