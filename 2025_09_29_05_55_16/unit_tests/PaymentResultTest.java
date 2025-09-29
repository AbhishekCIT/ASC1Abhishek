package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentResult model.
 * Covers constructors, getters, setters, and edge cases.
 */
class PaymentResultTest {
    @Test
    void testDefaultConstructor() {
        // Purpose: Test default constructor
        PaymentResult result = new PaymentResult();
        assertFalse(result.isSuccess());
        assertNull(result.getMessage());
        assertEquals(0.0, result.getAmount());
    }

    @Test
    void testAllArgsConstructor() {
        // Purpose: Test all-args constructor
        PaymentResult result = new PaymentResult(true, "Approved");
        assertTrue(result.isSuccess());
        assertEquals("Approved", result.getMessage());
    }

    @Test
    void testSettersAndGetters() {
        // Purpose: Test setters and getters, including edge cases
        PaymentResult result = new PaymentResult();
        result.setSuccess(true);
        assertTrue(result.isSuccess());
        result.setSuccess(false);
        assertFalse(result.isSuccess());
        result.setMessage("Declined");
        assertEquals("Declined", result.getMessage());
        result.setMessage(null);
        assertNull(result.getMessage());
        result.setAmount(100.0);
        assertEquals(100.0, result.getAmount());
        result.setAmount(0.0);
        assertEquals(0.0, result.getAmount());
        result.setAmount(-50.0);
        assertEquals(-50.0, result.getAmount());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
