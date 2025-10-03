package com.example.airbooking.service;

import com.example.airbooking.model.Payment;
import com.example.airbooking.model.PaymentInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PaymentGatewayService.
 */
public class PaymentGatewayServiceTest {
    /**
     * Test processPayment with valid card number (success).
     */
    @Test
    void testProcessPayment_Success() {
        PaymentGatewayService service = new PaymentGatewayService();
        PaymentInfo info = new PaymentInfo();
        info.setAmount(100.0);
        info.setPaymentMethod("CARD");
        info.setCardNumber("1234567890123456");
        Payment payment = service.processPayment(info);
        assertEquals("SUCCESS", payment.getPaymentStatus());
        assertEquals(100.0, payment.getAmount());
        assertEquals("CARD", payment.getPaymentMethod());
        assertNotNull(payment.getPaymentDate());
    }

    /**
     * Test processPayment with invalid card number (failure).
     */
    @Test
    void testProcessPayment_Failure() {
        PaymentGatewayService service = new PaymentGatewayService();
        PaymentInfo info = new PaymentInfo();
        info.setAmount(50.0);
        info.setPaymentMethod("CARD");
        info.setCardNumber("123");
        Payment payment = service.processPayment(info);
        assertEquals("FAILED", payment.getPaymentStatus());
        assertEquals(50.0, payment.getAmount());
    }

    /**
     * Test processPayment with null card number (edge case).
     */
    @Test
    void testProcessPayment_NullCardNumber() {
        PaymentGatewayService service = new PaymentGatewayService();
        PaymentInfo info = new PaymentInfo();
        info.setAmount(10.0);
        info.setPaymentMethod("CARD");
        info.setCardNumber(null);
        Payment payment = service.processPayment(info);
        assertEquals("FAILED", payment.getPaymentStatus());
    }
}
