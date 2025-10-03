package com.airtransport.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentEntity.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class PaymentEntityTest {
    private PaymentEntity paymentEntity;

    @BeforeEach
    void setUp() {
        paymentEntity = new PaymentEntity();
    }

    /**
     * Test setting and getting paymentId (normal case).
     */
    @Test
    void testPaymentId_Normal() {
        paymentEntity.setPaymentId("P123");
        assertEquals("P123", paymentEntity.getPaymentId());
    }

    /**
     * Test setting and getting bookingId (normal case).
     */
    @Test
    void testBookingId_Normal() {
        paymentEntity.setBookingId("B456");
        assertEquals("B456", paymentEntity.getBookingId());
    }

    /**
     * Test setting and getting amount (normal case).
     */
    @Test
    void testAmount_Normal() {
        paymentEntity.setAmount(150.0);
        assertEquals(150.0, paymentEntity.getAmount());
    }

    /**
     * Test setting and getting status (normal case).
     */
    @Test
    void testStatus_Normal() {
        paymentEntity.setStatus("SUCCESS");
        assertEquals("SUCCESS", paymentEntity.getStatus());
    }

    /**
     * Test setting and getting paymentDate (normal case).
     */
    @Test
    void testPaymentDate_Normal() {
        LocalDateTime now = LocalDateTime.now();
        paymentEntity.setPaymentDate(now);
        assertEquals(now, paymentEntity.getPaymentDate());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        paymentEntity.setPaymentId(null);
        paymentEntity.setBookingId(null);
        paymentEntity.setStatus(null);
        paymentEntity.setPaymentDate(null);
        assertNull(paymentEntity.getPaymentId());
        assertNull(paymentEntity.getBookingId());
        assertNull(paymentEntity.getStatus());
        assertNull(paymentEntity.getPaymentDate());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        paymentEntity.setPaymentId("");
        paymentEntity.setBookingId("");
        paymentEntity.setStatus("");
        assertEquals("", paymentEntity.getPaymentId());
        assertEquals("", paymentEntity.getBookingId());
        assertEquals("", paymentEntity.getStatus());
    }

    /**
     * Test boundary case: negative amount.
     */
    @Test
    void testSetters_NegativeAmount() {
        paymentEntity.setAmount(-10.0);
        assertEquals(-10.0, paymentEntity.getAmount());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
