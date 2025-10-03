package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Payment entity.
 * Covers constructors, builder, getters, setters, equals, hashCode, and toString.
 */
class PaymentTest {
    private Payment payment;
    private Booking booking;
    private BigDecimal amount;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        amount = BigDecimal.valueOf(350);
        payment = new Payment("PMT123", booking, amount, "stripe", "SUCCESS");
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        assertEquals("PMT123", payment.getPaymentId());
        assertEquals(booking, payment.getBooking());
        assertEquals(amount, payment.getAmount());
        assertEquals("stripe", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    @DisplayName("Test setters and no-args constructor")
    void testSettersAndNoArgsConstructor() {
        Payment p = new Payment();
        p.setPaymentId("PMT999");
        p.setBooking(booking);
        p.setAmount(BigDecimal.valueOf(100));
        p.setMethod("paypal");
        p.setStatus("FAILED");
        assertEquals("PMT999", p.getPaymentId());
        assertEquals(booking, p.getBooking());
        assertEquals(BigDecimal.valueOf(100), p.getAmount());
        assertEquals("paypal", p.getMethod());
        assertEquals("FAILED", p.getStatus());
    }

    @Test
    @DisplayName("Test builder pattern")
    void testBuilder() {
        Payment p = Payment.builder()
                .paymentId("PMT777")
                .booking(booking)
                .amount(BigDecimal.valueOf(200))
                .method("stripe")
                .status("PENDING")
                .build();
        assertEquals("PMT777", p.getPaymentId());
        assertEquals("PENDING", p.getStatus());
    }

    @Test
    @DisplayName("Test equals and hashCode")
    void testEqualsAndHashCode() {
        Payment p1 = new Payment("PMT123", booking, amount, "stripe", "SUCCESS");
        Payment p2 = new Payment("PMT123", booking, amount, "stripe", "SUCCESS");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Test toString does not throw")
    void testToString() {
        assertDoesNotThrow(() -> payment.toString());
    }

    @Test
    @DisplayName("Test edge case: null fields")
    void testNullFields() {
        Payment p = new Payment(null, null, null, null, null);
        assertNull(p.getPaymentId());
        assertNull(p.getBooking());
        assertNull(p.getAmount());
        assertNull(p.getMethod());
        assertNull(p.getStatus());
    }
}
