package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Payment entity.
 */
class PaymentTest {
    private Payment payment;
    private Booking booking;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        booking = new Booking();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testId() {
        payment.setId(200L);
        assertEquals(200L, payment.getId());
    }

    @Test
    @DisplayName("Test setting and getting booking")
    void testBooking() {
        payment.setBooking(booking);
        assertSame(booking, payment.getBooking());
    }

    @Test
    @DisplayName("Test setting and getting amount")
    void testAmount() {
        payment.setAmount(123.45);
        assertEquals(123.45, payment.getAmount());
    }

    @Test
    @DisplayName("Test setting and getting status")
    void testStatus() {
        payment.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", payment.getStatus());
    }

    @Test
    @DisplayName("Test setting and getting paymentMethod")
    void testPaymentMethod() {
        payment.setPaymentMethod("CARD");
        assertEquals("CARD", payment.getPaymentMethod());
    }

    @Test
    @DisplayName("Test setting and getting transactionId")
    void testTransactionId() {
        payment.setTransactionId("TXN123");
        assertEquals("TXN123", payment.getTransactionId());
    }

    @Test
    @DisplayName("Test setting and getting createdAt")
    void testCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        payment.setCreatedAt(now);
        assertEquals(now, payment.getCreatedAt());
    }

    @Test
    @DisplayName("Test null values for fields")
    void testNullFields() {
        payment.setBooking(null);
        payment.setAmount(null);
        payment.setStatus(null);
        payment.setPaymentMethod(null);
        payment.setTransactionId(null);
        payment.setCreatedAt(null);
        assertNull(payment.getBooking());
        assertNull(payment.getAmount());
        assertNull(payment.getStatus());
        assertNull(payment.getPaymentMethod());
        assertNull(payment.getTransactionId());
        assertNull(payment.getCreatedAt());
    }
}
