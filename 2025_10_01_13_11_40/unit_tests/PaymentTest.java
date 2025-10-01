package com.example.airlinebooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Payment entity.
 */
class PaymentTest {
    private Payment payment;
    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = Booking.builder().bookingId("B001").build();
        payment = Payment.builder()
                .paymentId("P001")
                .booking(booking)
                .amount(5000.0)
                .status("SUCCESS")
                .paymentDate(LocalDateTime.now())
                .build();
    }

    /**
     * Test normal creation of Payment entity.
     */
    @Test
    @DisplayName("Payment entity is created with all fields set")
    void testPaymentCreation_Normal() {
        assertEquals("P001", payment.getPaymentId());
        assertEquals(booking, payment.getBooking());
        assertEquals(5000.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
        assertNotNull(payment.getPaymentDate());
    }

    /**
     * Test edge case: null status.
     */
    @Test
    @DisplayName("Payment with null status should allow setting and getting")
    void testPayment_NullStatus() {
        payment.setStatus(null);
        assertNull(payment.getStatus());
    }

    /**
     * Test boundary case: empty paymentId.
     */
    @Test
    @DisplayName("Payment with empty paymentId")
    void testPayment_EmptyPaymentId() {
        payment.setPaymentId("");
        assertEquals("", payment.getPaymentId());
    }

    /**
     * Test error scenario: null booking.
     */
    @Test
    @DisplayName("Payment with null booking should allow setting and getting")
    void testPayment_NullBooking() {
        payment.setBooking(null);
        assertNull(payment.getBooking());
    }

    /**
     * Test error scenario: negative amount.
     */
    @Test
    @DisplayName("Payment with negative amount")
    void testPayment_NegativeAmount() {
        payment.setAmount(-100.0);
        assertEquals(-100.0, payment.getAmount());
    }

    /**
     * Test edge case: null paymentDate.
     */
    @Test
    @DisplayName("Payment with null paymentDate should allow setting and getting")
    void testPayment_NullPaymentDate() {
        payment.setPaymentDate(null);
        assertNull(payment.getPaymentDate());
    }
}
