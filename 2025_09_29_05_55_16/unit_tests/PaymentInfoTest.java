package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentInfo model.
 * Covers all getters, setters, and edge cases.
 */
class PaymentInfoTest {
    private PaymentInfo paymentInfo;

    @BeforeEach
    void setUp() {
        paymentInfo = new PaymentInfo();
    }

    @Test
    void testCardNumberGetterSetter() {
        // Purpose: Test normal and null values for cardNumber
        paymentInfo.setCardNumber("4111111111111111");
        assertEquals("4111111111111111", paymentInfo.getCardNumber());
        paymentInfo.setCardNumber(null);
        assertNull(paymentInfo.getCardNumber());
    }

    @Test
    void testCardHolderGetterSetter() {
        // Purpose: Test normal and null values for cardHolder
        paymentInfo.setCardHolder("John Doe");
        assertEquals("John Doe", paymentInfo.getCardHolder());
        paymentInfo.setCardHolder(null);
        assertNull(paymentInfo.getCardHolder());
    }

    @Test
    void testExpiryDateGetterSetter() {
        // Purpose: Test normal and null values for expiryDate
        paymentInfo.setExpiryDate("12/25");
        assertEquals("12/25", paymentInfo.getExpiryDate());
        paymentInfo.setExpiryDate(null);
        assertNull(paymentInfo.getExpiryDate());
    }

    @Test
    void testCvvGetterSetter() {
        // Purpose: Test normal and null values for cvv
        paymentInfo.setCvv("123");
        assertEquals("123", paymentInfo.getCvv());
        paymentInfo.setCvv(null);
        assertNull(paymentInfo.getCvv());
    }

    @Test
    void testAmountGetterSetter() {
        // Purpose: Test normal, zero, and negative values for amount
        paymentInfo.setAmount(100.0);
        assertEquals(100.0, paymentInfo.getAmount());
        paymentInfo.setAmount(0.0);
        assertEquals(0.0, paymentInfo.getAmount());
        paymentInfo.setAmount(-50.0);
        assertEquals(-50.0, paymentInfo.getAmount());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
