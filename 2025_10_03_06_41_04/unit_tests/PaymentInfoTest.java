package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentInfo DTO.
 */
public class PaymentInfoTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testFields() {
        PaymentInfo info = new PaymentInfo();
        info.setAmount(100.0);
        info.setPaymentMethod("CARD");
        info.setCardNumber("1234567890123456");
        info.setCardExpiry("12/30");
        info.setCardCvv("123");
        info.setCardHolderName("John Doe");

        assertEquals(100.0, info.getAmount());
        assertEquals("CARD", info.getPaymentMethod());
        assertEquals("1234567890123456", info.getCardNumber());
        assertEquals("12/30", info.getCardExpiry());
        assertEquals("123", info.getCardCvv());
        assertEquals("John Doe", info.getCardHolderName());
    }

    /**
     * Test edge case: null card number.
     */
    @Test
    void testNullCardNumber() {
        PaymentInfo info = new PaymentInfo();
        info.setCardNumber(null);
        assertNull(info.getCardNumber());
    }
}
