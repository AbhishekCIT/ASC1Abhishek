package com.asc.airbooking.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for PaymentService.
 * Covers normal, edge, and error scenarios for processPayment.
 */
class PaymentServiceTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();
    }

    /**
     * Purpose: Test successful payment processing (Stripe returns succeeded).
     */
    @Test
    void testProcessPayment_Success() throws StripeException {
        PaymentIntent intent = mock(PaymentIntent.class);
        when(intent.getStatus()).thenReturn("succeeded");
        try (MockedStatic<PaymentIntent> mockedStatic = mockStatic(PaymentIntent.class)) {
            mockedStatic.when(() -> PaymentIntent.create(any(PaymentIntentCreateParams.class))).thenReturn(intent);
            boolean result = paymentService.processPayment(100.0, "tok_abc");
            assertTrue(result);
        }
    }

    /**
     * Purpose: Test failed payment processing (Stripe returns failed status).
     */
    @Test
    void testProcessPayment_FailedStatus() throws StripeException {
        PaymentIntent intent = mock(PaymentIntent.class);
        when(intent.getStatus()).thenReturn("failed");
        try (MockedStatic<PaymentIntent> mockedStatic = mockStatic(PaymentIntent.class)) {
            mockedStatic.when(() -> PaymentIntent.create(any(PaymentIntentCreateParams.class))).thenReturn(intent);
            boolean result = paymentService.processPayment(100.0, "tok_abc");
            assertFalse(result);
        }
    }

    /**
     * Purpose: Test StripeException thrown during payment processing (should return false).
     */
    @Test
    void testProcessPayment_StripeException() throws StripeException {
        try (MockedStatic<PaymentIntent> mockedStatic = mockStatic(PaymentIntent.class)) {
            mockedStatic.when(() -> PaymentIntent.create(any(PaymentIntentCreateParams.class))).thenThrow(new StripeException("error", null, null, 400, null));
            boolean result = paymentService.processPayment(100.0, "tok_abc");
            assertFalse(result);
        }
    }

    /**
     * Purpose: Test edge case with zero and negative amount.
     */
    @Test
    void testProcessPayment_ZeroAndNegativeAmount() throws StripeException {
        PaymentIntent intent = mock(PaymentIntent.class);
        when(intent.getStatus()).thenReturn("succeeded");
        try (MockedStatic<PaymentIntent> mockedStatic = mockStatic(PaymentIntent.class)) {
            mockedStatic.when(() -> PaymentIntent.create(any(PaymentIntentCreateParams.class))).thenReturn(intent);
            assertTrue(paymentService.processPayment(0.0, "tok_abc"));
            assertTrue(paymentService.processPayment(-100.0, "tok_abc"));
        }
    }
}
