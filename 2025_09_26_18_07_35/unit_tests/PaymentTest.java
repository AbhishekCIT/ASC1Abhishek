package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity.
 */
public class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    @DisplayName("Test getters and setters with normal values")
    void testGettersAndSettersNormal() {
        String paymentId = "P001";
        String bookingRef = "BR123";
        double amount = 350.0;
        String status = "CONFIRMED";
        LocalDateTime now = LocalDateTime.now();

        payment.setPaymentId(paymentId);
        payment.setBookingRef(bookingRef);
        payment.setAmount(amount);
        payment.setStatus(status);
        payment.setPaymentDate(now);

        assertEquals(paymentId, payment.getPaymentId());
        assertEquals(bookingRef, payment.getBookingRef());
        assertEquals(amount, payment.getAmount());
        assertEquals(status, payment.getStatus());
        assertEquals(now, payment.getPaymentDate());
    }

    /**
     * Test setters and getters with null values.
     */
    @Test
    @DisplayName("Test setters and getters with null values")
    void testNullValues() {
        payment.setPaymentId(null);
        payment.setBookingRef(null);
        payment.setStatus(null);
        payment.setPaymentDate(null);

        assertNull(payment.getPaymentId());
        assertNull(payment.getBookingRef());
        assertNull(payment.getStatus());
        assertNull(payment.getPaymentDate());
    }

    /**
     * Test setters and getters with empty strings.
     */
    @Test
    @DisplayName("Test setters and getters with empty strings")
    void testEmptyStrings() {
        payment.setPaymentId("");
        payment.setBookingRef("");
        payment.setStatus("");

        assertEquals("", payment.getPaymentId());
        assertEquals("", payment.getBookingRef());
        assertEquals("", payment.getStatus());
    }

    /**
     * Test amount with zero, negative, and large values.
     */
    @Test
    @DisplayName("Test amount edge cases")
    void testAmountEdgeCases() {
        payment.setAmount(0.0);
        assertEquals(0.0, payment.getAmount());
        payment.setAmount(-100.0);
        assertEquals(-100.0, payment.getAmount());
        payment.setAmount(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, payment.getAmount());
    }

    /**
     * Test boundary values for paymentDate.
     */
    @Test
    @DisplayName("Test boundary values for paymentDate")
    void testPaymentDateBoundaries() {
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime max = LocalDateTime.MAX;
        payment.setPaymentDate(min);
        assertEquals(min, payment.getPaymentDate());
        payment.setPaymentDate(max);
        assertEquals(max, payment.getPaymentDate());
    }
}
