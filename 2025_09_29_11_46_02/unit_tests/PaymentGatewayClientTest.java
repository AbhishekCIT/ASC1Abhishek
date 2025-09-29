package com.example.booking.client;

import com.example.booking.model.PaymentInfo;
import org.junit.jupiter.api.*;

/**
 * Unit tests for PaymentGatewayClient.
 */
class PaymentGatewayClientTest {
    private PaymentGatewayClient paymentGatewayClient;

    @BeforeEach
    void setUp() {
        paymentGatewayClient = new PaymentGatewayClient();
    }

    /**
     * Test successful payment with valid token.
     */
    @Test
    void testProcessPayment_Success() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setToken("validToken");
        boolean result = paymentGatewayClient.processPayment(paymentInfo);
        Assertions.assertTrue(result, "Payment should succeed with valid token");
    }

    /**
     * Test payment failure when token is 'fail'.
     */
    @Test
    void testProcessPayment_FailToken() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setToken("fail");
        boolean result = paymentGatewayClient.processPayment(paymentInfo);
        Assertions.assertFalse(result, "Payment should fail when token is 'fail'");
    }

    /**
     * Test payment failure when PaymentInfo is null (edge case).
     */
    @Test
    void testProcessPayment_NullPaymentInfo() {
        boolean result = paymentGatewayClient.processPayment(null);
        Assertions.assertFalse(result, "Payment should fail when PaymentInfo is null");
    }

    /**
     * Test payment failure when token is null (boundary case).
     */
    @Test
    void testProcessPayment_NullToken() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setToken(null);
        boolean result = paymentGatewayClient.processPayment(paymentInfo);
        Assertions.assertFalse(result, "Payment should fail when token is null");
    }

    /**
     * Test payment with empty string token (boundary case).
     */
    @Test
    void testProcessPayment_EmptyToken() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setToken("");
        boolean result = paymentGatewayClient.processPayment(paymentInfo);
        Assertions.assertTrue(result, "Payment should succeed with empty string token");
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
