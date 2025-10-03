package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentInfo.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class PaymentInfoTest {
    private PaymentInfo paymentInfo;

    @BeforeEach
    void setUp() {
        paymentInfo = new PaymentInfo();
    }

    /**
     * Test setting and getting cardNumber (normal case).
     */
    @Test
    void testCardNumber_Normal() {
        paymentInfo.setCardNumber("4111111111111111");
        assertEquals("4111111111111111", paymentInfo.getCardNumber());
    }

    /**
     * Test setting and getting cardHolderName (normal case).
     */
    @Test
    void testCardHolderName_Normal() {
        paymentInfo.setCardHolderName("John Doe");
        assertEquals("John Doe", paymentInfo.getCardHolderName());
    }

    /**
     * Test setting and getting expiryDate (normal case).
     */
    @Test
    void testExpiryDate_Normal() {
        paymentInfo.setExpiryDate("12/25");
        assertEquals("12/25", paymentInfo.getExpiryDate());
    }

    /**
     * Test setting and getting cvv (normal case).
     */
    @Test
    void testCvv_Normal() {
        paymentInfo.setCvv("123");
        assertEquals("123", paymentInfo.getCvv());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        paymentInfo.setCardNumber(null);
        paymentInfo.setCardHolderName(null);
        paymentInfo.setExpiryDate(null);
        paymentInfo.setCvv(null);
        assertNull(paymentInfo.getCardNumber());
        assertNull(paymentInfo.getCardHolderName());
        assertNull(paymentInfo.getExpiryDate());
        assertNull(paymentInfo.getCvv());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        paymentInfo.setCardNumber("");
        paymentInfo.setCardHolderName("");
        paymentInfo.setExpiryDate("");
        paymentInfo.setCvv("");
        assertEquals("", paymentInfo.getCardNumber());
        assertEquals("", paymentInfo.getCardHolderName());
        assertEquals("", paymentInfo.getExpiryDate());
        assertEquals("", paymentInfo.getCvv());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
