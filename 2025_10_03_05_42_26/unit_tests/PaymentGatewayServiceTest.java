package com.airtransport.service;

import com.airtransport.model.PaymentInfo;
import com.airtransport.model.PaymentResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for PaymentGatewayService.
 * Covers normal, edge, boundary, and error scenarios for processPayment and processPaymentForBooking.
 */
class PaymentGatewayServiceTest {
    private PaymentGatewayService paymentGatewayService;

    @BeforeEach
    void setUp() {
        paymentGatewayService = new PaymentGatewayService();
    }

    /**
     * Test processing payment with valid info (normal case).
     */
    @Test
    void testProcessPayment_Normal() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("4111111111111111");
        PaymentResponse response = paymentGatewayService.processPayment("B123", paymentInfo);
        assertNotNull(response);
        assertEquals("SUCCESS", response.getPaymentStatus());
    }

    /**
     * Test processing payment with invalid info (error scenario).
     */
    @Test
    void testProcessPayment_InvalidInfo() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber(null);
        Exception ex = assertThrows(RuntimeException.class, () -> paymentGatewayService.processPayment("B123", paymentInfo));
        assertEquals("Invalid payment details", ex.getMessage());
    }

    /**
     * Test processing payment with null PaymentInfo (error scenario).
     */
    @Test
    void testProcessPayment_NullPaymentInfo() {
        Exception ex = assertThrows(RuntimeException.class, () -> paymentGatewayService.processPayment("B123", null));
        assertEquals("Invalid payment details", ex.getMessage());
    }

    /**
     * Test processPaymentForBooking always returns true (normal case).
     */
    @Test
    void testProcessPaymentForBooking_Normal() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("4111111111111111");
        boolean result = paymentGatewayService.processPaymentForBooking(100.0, paymentInfo);
        assertTrue(result);
    }

    /**
     * Test processPaymentForBooking with null PaymentInfo (edge case).
     */
    @Test
    void testProcessPaymentForBooking_NullPaymentInfo() {
        boolean result = paymentGatewayService.processPaymentForBooking(100.0, null);
        assertTrue(result);
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
