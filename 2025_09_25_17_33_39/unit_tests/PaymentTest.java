package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

    @Test
    @DisplayName("Should set and get id correctly")
    void testIdGetterSetter() {
        payment.setId(201L);
        assertEquals(201L, payment.getId());
    }

    @Test
    @DisplayName("Should set and get bookingId correctly")
    void testBookingIdGetterSetter() {
        payment.setBookingId(301L);
        assertEquals(301L, payment.getBookingId());
    }

    @Test
    @DisplayName("Should set and get status correctly")
    void testStatusGetterSetter() {
        payment.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", payment.getStatus());
    }

    @Test
    @DisplayName("Should set and get amount correctly")
    void testAmountGetterSetter() {
        payment.setAmount(499.99);
        assertEquals(499.99, payment.getAmount());
    }

    @Test
    @DisplayName("Should set and get paymentDate correctly")
    void testPaymentDateGetterSetter() {
        LocalDate date = LocalDate.of(2025, 10, 2);
        payment.setPaymentDate(date);
        assertEquals(date, payment.getPaymentDate());
    }

    @Test
    @DisplayName("Should handle null values for fields")
    void testNullFields() {
        payment.setStatus(null);
        payment.setAmount(null);
        payment.setPaymentDate(null);
        assertNull(payment.getStatus());
        assertNull(payment.getAmount());
        assertNull(payment.getPaymentDate());
    }
}
