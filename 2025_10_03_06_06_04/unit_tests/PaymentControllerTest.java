package com.example.airtransport.controller;

import com.example.airtransport.model.PaymentInfo;
import com.example.airtransport.model.PaymentResponse;
import com.example.airtransport.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PaymentController.
 * Covers normal, edge, and error scenarios for payment processing.
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

    @Nested
    @DisplayName("processPayment() tests")
    class ProcessPaymentTests {
        @Test
        @DisplayName("Should return SUCCESS PaymentResponse when payment is successful")
        void testProcessPaymentSuccess() {
            // Arrange
            String bookingId = "B456";
            PaymentInfo paymentInfo = new PaymentInfo();
            when(paymentService.processPayment(anyString(), any())).thenReturn(true);

            // Act
            ResponseEntity<PaymentResponse> response = paymentController.processPayment(bookingId, paymentInfo);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals("SUCCESS", response.getBody().getPaymentStatus());
            assertNotNull(response.getBody().getTransactionId());
        }

        @Test
        @DisplayName("Should return FAILED PaymentResponse when payment fails")
        void testProcessPaymentFailure() {
            // Arrange
            String bookingId = "B456";
            PaymentInfo paymentInfo = new PaymentInfo();
            when(paymentService.processPayment(anyString(), any())).thenReturn(false);

            // Act
            ResponseEntity<PaymentResponse> response = paymentController.processPayment(bookingId, paymentInfo);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals("FAILED", response.getBody().getPaymentStatus());
            assertNull(response.getBody().getTransactionId());
        }

        @Test
        @DisplayName("Should handle null PaymentInfo (edge case)")
        void testProcessPaymentNullPaymentInfo() {
            // Purpose: Should throw exception if paymentInfo is null (validation handled by framework)
            assertThrows(NullPointerException.class, () -> paymentController.processPayment("B456", null));
        }

        @Test
        @DisplayName("Should handle service exception (error case)")
        void testProcessPaymentServiceException() {
            // Arrange
            String bookingId = "B456";
            PaymentInfo paymentInfo = new PaymentInfo();
            when(paymentService.processPayment(anyString(), any())).thenThrow(new RuntimeException("Payment error"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> paymentController.processPayment(bookingId, paymentInfo));
        }
    }
}
