package com.airtransport.controller;

import com.airtransport.model.Payment;
import com.airtransport.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for PaymentController.
 * Covers normal, edge, and error scenarios for payment processing endpoint.
 */
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test processPayment with valid input.
     * Purpose: Should process payment and return Payment object.
     */
    @Test
    void testProcessPayment_Success() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("bookingId", "booking123");
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        requestBody.put("paymentDetails", paymentDetails);

        Payment mockPayment = new Payment();
        when(paymentService.processPayment("booking123", paymentDetails)).thenReturn(mockPayment);
        Payment result = paymentController.processPayment(requestBody);
        assertNotNull(result);
        verify(paymentService, times(1)).processPayment("booking123", paymentDetails);
    }

    /**
     * Test processPayment with missing bookingId (edge case).
     * Purpose: Should throw NullPointerException or IllegalArgumentException.
     */
    @Test
    void testProcessPayment_MissingBookingId() {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        requestBody.put("paymentDetails", paymentDetails);
        assertThrows(NullPointerException.class, () -> paymentController.processPayment(requestBody));
    }

    /**
     * Test processPayment with missing paymentDetails (edge case).
     * Purpose: Should throw NullPointerException or IllegalArgumentException.
     */
    @Test
    void testProcessPayment_MissingPaymentDetails() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("bookingId", "booking123");
        assertThrows(NullPointerException.class, () -> paymentController.processPayment(requestBody));
    }

    /**
     * Test processPayment with invalid bookingId (error case).
     * Purpose: Should throw exception from service.
     */
    @Test
    void testProcessPayment_InvalidBookingId() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("bookingId", "invalid");
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        requestBody.put("paymentDetails", paymentDetails);
        when(paymentService.processPayment("invalid", paymentDetails)).thenThrow(new IllegalArgumentException("Booking not found"));
        assertThrows(IllegalArgumentException.class, () -> paymentController.processPayment(requestBody));
    }
}
