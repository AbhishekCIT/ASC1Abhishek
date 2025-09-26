package com.example.airtransport.controller;

import com.example.airtransport.model.PaymentRequest;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
     * Test processing payment with valid request.
     */
    @Test
    @DisplayName("processPayment returns PaymentResponse for valid request")
    void testProcessPayment_ValidRequest_ReturnsResponse() {
        PaymentRequest request = new PaymentRequest();
        request.setBookingReference("BR12345678");
        request.setPaymentInfo(new Object());
        request.setAmount(300.00);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId("P123456");
        response.setStatus("SUCCESS");
        response.setTimestamp("2025-10-01T10:00:00");

        when(paymentService.processPayment(any(PaymentRequest.class))).thenReturn(response);

        ResponseEntity<PaymentResponse> result = paymentController.processPayment(request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("SUCCESS", result.getBody().getStatus());
        assertEquals("P123456", result.getBody().getPaymentId());
        verify(paymentService, times(1)).processPayment(any(PaymentRequest.class));
    }

    /**
     * Test processing payment with invalid payment info (error scenario).
     */
    @Test
    @DisplayName("processPayment throws exception for invalid payment info")
    void testProcessPayment_InvalidPaymentInfo_ThrowsException() {
        PaymentRequest request = new PaymentRequest();
        request.setBookingReference("BR12345678");
        request.setPaymentInfo(null);
        request.setAmount(300.00);

        when(paymentService.processPayment(any(PaymentRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid payment information"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentController.processPayment(request);
        });
        assertEquals("Invalid payment information", exception.getMessage());
        verify(paymentService, times(1)).processPayment(any(PaymentRequest.class));
    }

    /**
     * Test processing payment when payment fails (edge case).
     */
    @Test
    @DisplayName("processPayment returns FAILED status when payment fails")
    void testProcessPayment_PaymentFails_ReturnsFailedStatus() {
        PaymentRequest request = new PaymentRequest();
        request.setBookingReference("BR12345678");
        request.setPaymentInfo(new Object());
        request.setAmount(300.00);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId("P123457");
        response.setStatus("FAILED");
        response.setTimestamp("2025-10-01T10:05:00");

        when(paymentService.processPayment(any(PaymentRequest.class))).thenReturn(response);

        ResponseEntity<PaymentResponse> result = paymentController.processPayment(request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("FAILED", result.getBody().getStatus());
        assertEquals("P123457", result.getBody().getPaymentId());
        verify(paymentService, times(1)).processPayment(any(PaymentRequest.class));
    }
}
