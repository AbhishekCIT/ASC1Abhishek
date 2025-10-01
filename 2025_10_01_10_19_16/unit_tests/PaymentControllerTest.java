package com.airtransport.controller;

import com.airtransport.model.Payment;
import com.airtransport.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PaymentController.
 */
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test successful payment processing scenario.
     */
    @Test
    void testProcessPayment_Success() {
        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B789");
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        request.setPaymentDetails(paymentDetails);

        Payment payment = new Payment();
        payment.setStatus("SUCCESS");
        payment.setTransactionId("TX1234");

        when(paymentService.processPayment(anyString(), anyMap())).thenReturn(payment);

        ResponseEntity<?> response = paymentController.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PaymentController.PaymentResponse);
        PaymentController.PaymentResponse resp = (PaymentController.PaymentResponse) response.getBody();
        assertEquals("SUCCESS", resp.getPaymentStatus());
        assertEquals("TX1234", resp.getTransactionId());
    }

    /**
     * Test payment processing failure scenario.
     */
    @Test
    void testProcessPayment_Failure() {
        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B789");
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        request.setPaymentDetails(paymentDetails);

        Payment payment = new Payment();
        payment.setStatus("FAILED");

        when(paymentService.processPayment(anyString(), anyMap())).thenReturn(payment);

        ResponseEntity<?> response = paymentController.processPayment(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PaymentController.ErrorResponse);
        PaymentController.ErrorResponse resp = (PaymentController.ErrorResponse) response.getBody();
        assertEquals("Payment failed.", resp.getError());
    }

    /**
     * Test payment processing with invalid arguments (IllegalArgumentException).
     */
    @Test
    void testProcessPayment_InvalidArgument() {
        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId(null);
        request.setPaymentDetails(new HashMap<>());

        when(paymentService.processPayment(anyString(), anyMap())).thenThrow(new IllegalArgumentException("Invalid payment details"));

        ResponseEntity<?> response = paymentController.processPayment(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PaymentController.ErrorResponse);
        PaymentController.ErrorResponse resp = (PaymentController.ErrorResponse) response.getBody();
        assertEquals("Invalid payment details", resp.getError());
    }

    /**
     * Test payment processing with unexpected exception (internal server error).
     */
    @Test
    void testProcessPayment_InternalServerError() {
        PaymentController.PaymentRequest request = new PaymentController.PaymentRequest();
        request.setBookingId("B789");
        request.setPaymentDetails(new HashMap<>());

        when(paymentService.processPayment(anyString(), anyMap())).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<?> response = paymentController.processPayment(request);
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof PaymentController.ErrorResponse);
        PaymentController.ErrorResponse resp = (PaymentController.ErrorResponse) response.getBody();
        assertEquals("Internal server error.", resp.getError());
    }
}
