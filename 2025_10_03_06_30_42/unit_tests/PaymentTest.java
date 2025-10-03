package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Payment entity.
 * Covers normal, edge, boundary, and error scenarios for all methods.
 */
class PaymentTest {
    private Payment payment;
    private Booking booking;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        booking = new Booking();
        booking.setBookingId("BK123");
    }

    /**
     * Test setting and getting paymentId (normal scenario).
     */
    @Test
    void testPaymentId() {
        payment.setPaymentId("PM001");
        assertEquals("PM001", payment.getPaymentId());
    }

    /**
     * Test setting and getting booking (normal scenario).
     */
    @Test
    void testBooking() {
        payment.setBooking(booking);
        assertEquals(booking, payment.getBooking());
    }

    /**
     * Test setting and getting amount (normal scenario).
     */
    @Test
    void testAmount() {
        payment.setAmount(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), payment.getAmount());
    }

    /**
     * Test setting and getting status (normal scenario).
     */
    @Test
    void testStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    /**
     * Test setting and getting paymentDate (normal scenario).
     */
    @Test
    void testPaymentDate() {
        LocalDateTime now = LocalDateTime.now();
        payment.setPaymentDate(now);
        assertEquals(now, payment.getPaymentDate());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testNullValues() {
        payment.setBooking(null);
        payment.setAmount(null);
        payment.setStatus(null);
        payment.setPaymentDate(null);
        assertNull(payment.getBooking());
        assertNull(payment.getAmount());
        assertNull(payment.getStatus());
        assertNull(payment.getPaymentDate());
    }

    /**
     * Test boundary condition for amount (zero amount).
     */
    @Test
    void testAmountZero() {
        payment.setAmount(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, payment.getAmount());
    }
}
