package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentRequest.
 * Covers setters, getters, and edge cases.
 */
class PaymentRequestTest {

    /**
     * Purpose: Test setting and getting all fields.
     */
    @Test
    void testSettersAndGetters() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setToken("tok_abc");
        assertEquals(100.0, request.getAmount());
        assertEquals("tok_abc", request.getToken());
    }

    /**
     * Purpose: Test edge case with null values.
     */
    @Test
    void testNullValues() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(null);
        request.setToken(null);
        assertNull(request.getAmount());
        assertNull(request.getToken());
    }

    /**
     * Purpose: Test edge case with blank token.
     */
    @Test
    void testBlankToken() {
        PaymentRequest request = new PaymentRequest();
        request.setToken("");
        assertEquals("", request.getToken());
    }

    /**
     * Purpose: Test boundary conditions for amount (zero and negative).
     */
    @Test
    void testAmountBoundaryConditions() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(0.0);
        assertEquals(0.0, request.getAmount());
        request.setAmount(-100.0);
        assertEquals(-100.0, request.getAmount());
    }
}
