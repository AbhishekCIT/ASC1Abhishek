package com.airtransport.service;

import com.airtransport.model.Payment;
import com.airtransport.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PaymentService.
 */
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test successful payment processing scenario.
     */
    @Test
    void testProcessPayment_Success() {
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        paymentDetails.put("amount", 350.00);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArgument(0));
        Payment payment = paymentService.processPayment("B789", paymentDetails);
        assertNotNull(payment.getPaymentId());
        assertEquals("B789", payment.getBookingId());
        assertEquals(350.00, payment.getAmount());
        assertEquals("SUCCESS", payment.getStatus());
        assertNotNull(payment.getPaymentDate());
        assertNotNull(payment.getTransactionId());
    }

    /**
     * Test payment processing with missing card number (error scenario).
     */
    @Test
    void testProcessPayment_InvalidDetails() {
        Map<String, Object> paymentDetails = new HashMap<>();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                paymentService.processPayment("B789", paymentDetails));
        assertEquals("Invalid payment details", ex.getMessage());
    }
}
