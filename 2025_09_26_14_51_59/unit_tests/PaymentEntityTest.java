package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentEntity (POJO).
 */
class PaymentEntityTest {
    private PaymentEntity paymentEntity;

    @BeforeEach
    void setUp() {
        paymentEntity = new PaymentEntity();
    }

    /**
     * Test setting and getting paymentId.
     */
    @Test
    @DisplayName("Set and get paymentId")
    void testPaymentId() {
        paymentEntity.setPaymentId(1L);
        assertEquals(1L, paymentEntity.getPaymentId());
    }

    /**
     * Test setting and getting bookingId.
     */
    @Test
    @DisplayName("Set and get bookingId")
    void testBookingId() {
        paymentEntity.setBookingId(2L);
        assertEquals(2L, paymentEntity.getBookingId());
    }

    /**
     * Test setting and getting amount.
     */
    @Test
    @DisplayName("Set and get amount")
    void testAmount() {
        paymentEntity.setAmount(150.0);
        assertEquals(150.0, paymentEntity.getAmount());
    }

    /**
     * Test setting and getting paymentMethod.
     */
    @Test
    @DisplayName("Set and get paymentMethod")
    void testPaymentMethod() {
        paymentEntity.setPaymentMethod("CARD");
        assertEquals("CARD", paymentEntity.getPaymentMethod());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    @DisplayName("Set and get status")
    void testStatus() {
        paymentEntity.setStatus("SUCCESS");
        assertEquals("SUCCESS", paymentEntity.getStatus());
    }

    /**
     * Test setting and getting transactionId.
     */
    @Test
    @DisplayName("Set and get transactionId")
    void testTransactionId() {
        paymentEntity.setTransactionId("TXN123");
        assertEquals("TXN123", paymentEntity.getTransactionId());
    }

    /**
     * Test default values (should be null).
     */
    @Test
    @DisplayName("Default values are null")
    void testDefaultValues() {
        assertNull(paymentEntity.getPaymentId());
        assertNull(paymentEntity.getBookingId());
        assertNull(paymentEntity.getAmount());
        assertNull(paymentEntity.getPaymentMethod());
        assertNull(paymentEntity.getStatus());
        assertNull(paymentEntity.getTransactionId());
    }
}
