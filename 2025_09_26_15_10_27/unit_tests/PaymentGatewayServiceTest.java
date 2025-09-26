package com.example.airtransport.service;

import com.example.airtransport.model.Payment;
import com.example.airtransport.model.PaymentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentGatewayService.
 */
class PaymentGatewayServiceTest {

    private final PaymentGatewayService paymentGatewayService = new PaymentGatewayService();

    @Test
    @DisplayName("Test processPayment returns SUCCESS for valid payment info")
    void testProcessPayment_Success() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMethod("CARD");
        paymentInfo.setAmount(100.0);
        paymentInfo.setCardNumber("4111111111111111");
        paymentInfo.setCardExpiry("12/30");
        paymentInfo.setCardCvv("123");

        Payment payment = paymentGatewayService.processPayment(paymentInfo);
        assertNotNull(payment);
        assertNotNull(payment.getPaymentId());
        assertEquals("CARD", payment.getMethod());
        assertEquals(100.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    @DisplayName("Test processPayment with null method and zero amount")
    void testProcessPayment_NullMethodZeroAmount() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMethod(null);
        paymentInfo.setAmount(0.0);
        Payment payment = paymentGatewayService.processPayment(paymentInfo);
        assertNotNull(payment);
        assertNull(payment.getMethod());
        assertEquals(0.0, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    @DisplayName("Test processPayment with null PaymentInfo throws NullPointerException")
    void testProcessPayment_NullPaymentInfo() {
        assertThrows(NullPointerException.class, () -> paymentGatewayService.processPayment(null));
    }
}
