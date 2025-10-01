package com.example.airlinebooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Payment entity.
 * Covers all getters, setters, and edge cases.
 */
class PaymentTest {
    private Payment payment;
    private Booking booking;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        booking = new Booking();
        now = LocalDateTime.now();
    }

    @Test
    @DisplayName("Set and get paymentId")
    void testPaymentId() {
        payment.setPaymentId("PAY001");
        assertEquals("PAY001", payment.getPaymentId());
    }

    @Test
    @DisplayName("Set and get booking")
    void testBooking() {
        payment.setBooking(booking);
        assertEquals(booking, payment.getBooking());
        payment.setBooking(null);
        assertNull(payment.getBooking());
    }

    @Test
    @DisplayName("Set and get amount")
    void testAmount() {
        BigDecimal amount = new BigDecimal("123.45");
        payment.setAmount(amount);
        assertEquals(amount, payment.getAmount());
        payment.setAmount(null);
        assertNull(payment.getAmount());
    }

    @Test
    @DisplayName("Set and get status")
    void testStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        payment.setStatus(null);
        assertNull(payment.getStatus());
    }

    @Test
    @DisplayName("Set and get paymentDate")
    void testPaymentDate() {
        payment.setPaymentDate(now);
        assertEquals(now, payment.getPaymentDate());
        payment.setPaymentDate(null);
        assertNull(payment.getPaymentDate());
    }

    @Test
    @DisplayName("Edge case: All fields null")
    void testAllFieldsNull() {
        Payment empty = new Payment();
        assertNull(empty.getPaymentId());
        assertNull(empty.getBooking());
        assertNull(empty.getAmount());
        assertNull(empty.getStatus());
        assertNull(empty.getPaymentDate());
    }
}
