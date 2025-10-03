package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity
 */
public class PaymentTest {
    private Payment payment;
    private Booking booking;
    private LocalDateTime paidAt;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        booking = new Booking();
        paidAt = LocalDateTime.now();
    }

    /**
     * Test setting and getting paymentId
     */
    @Test
    void testPaymentId() {
        payment.setPaymentId(100L);
        assertEquals(100L, payment.getPaymentId());
    }

    /**
     * Test setting and getting booking
     */
    @Test
    void testBooking() {
        payment.setBooking(booking);
        assertEquals(booking, payment.getBooking());
    }

    /**
     * Test setting and getting amount
     */
    @Test
    void testAmount() {
        payment.setAmount(250.75);
        assertEquals(250.75, payment.getAmount());
    }

    /**
     * Test setting and getting status
     */
    @Test
    void testStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    /**
     * Test setting and getting transactionId
     */
    @Test
    void testTransactionId() {
        payment.setTransactionId("TXN12345");
        assertEquals("TXN12345", payment.getTransactionId());
    }

    /**
     * Test setting and getting paidAt
     */
    @Test
    void testPaidAt() {
        payment.setPaidAt(paidAt);
        assertEquals(paidAt, payment.getPaidAt());
    }

    /**
     * Test edge case: negative amount
     */
    @Test
    void testNegativeAmount() {
        payment.setAmount(-100.0);
        assertEquals(-100.0, payment.getAmount());
    }

    /**
     * Test edge case: null values
     */
    @Test
    void testNullValues() {
        payment.setBooking(null);
        payment.setStatus(null);
        payment.setTransactionId(null);
        payment.setPaidAt(null);
        assertNull(payment.getBooking());
        assertNull(payment.getStatus());
        assertNull(payment.getTransactionId());
        assertNull(payment.getPaidAt());
    }
}
