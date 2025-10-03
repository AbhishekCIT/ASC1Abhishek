package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentResponse.
 * Covers setters, getters, and edge cases.
 */
class PaymentResponseTest {

    /**
     * Purpose: Test setting and getting paymentStatus and transactionId.
     */
    @Test
    void testSettersAndGetters() {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus("success");
        response.setTransactionId("txn_123");
        assertEquals("success", response.getPaymentStatus());
        assertEquals("txn_123", response.getTransactionId());
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus(null);
        response.setTransactionId(null);
        assertNull(response.getPaymentStatus());
        assertNull(response.getTransactionId());
    }

    /**
     * Purpose: Test edge case with blank values.
     */
    @Test
    void testBlankValues() {
        PaymentResponse response = new PaymentResponse();
        response.setPaymentStatus("");
        response.setTransactionId("");
        assertEquals("", response.getPaymentStatus());
        assertEquals("", response.getTransactionId());
    }
}
