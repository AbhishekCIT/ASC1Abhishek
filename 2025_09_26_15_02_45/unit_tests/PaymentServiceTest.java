package com.example.airbooking.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private final PaymentService paymentService = new PaymentService();

    @Test
    @DisplayName("processPayment - normal scenario")
    void testProcessPaymentNormal() {
        Object paymentInfo = new Object();
        assertTrue(paymentService.processPayment(paymentInfo));
    }

    @Test
    @DisplayName("processPayment - null paymentInfo")
    void testProcessPaymentNull() {
        assertTrue(paymentService.processPayment(null));
    }
}
