package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentRequest.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class PaymentRequestTest {
    private PaymentRequest paymentRequest;

    @BeforeEach
    void setUp() {
        paymentRequest = new PaymentRequest();
    }

    /**
     * Test setting and getting bookingId (normal case).
     */
    @Test
    void testBookingId_Normal() {
        paymentRequest.setBookingId("B123");
        assertEquals("B123", paymentRequest.getBookingId());
    }

    /**
     * Test setting and getting paymentInfo (normal case).
     */
    @Test
    void testPaymentInfo_Normal() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentRequest.setPaymentInfo(paymentInfo);
        assertEquals(paymentInfo, paymentRequest.getPaymentInfo());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        paymentRequest.setBookingId(null);
        paymentRequest.setPaymentInfo(null);
        assertNull(paymentRequest.getBookingId());
        assertNull(paymentRequest.getPaymentInfo());
    }

    /**
     * Test boundary case: empty string for bookingId.
     */
    @Test
    void testSetters_EmptyString() {
        paymentRequest.setBookingId("");
        assertEquals("", paymentRequest.getBookingId());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
