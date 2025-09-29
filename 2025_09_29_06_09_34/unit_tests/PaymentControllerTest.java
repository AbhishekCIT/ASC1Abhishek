package com.airbook.controller;

import com.airbook.model.Payment;
import com.airbook.service.PaymentService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PaymentController
 */
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test processPayment returns processed payment for valid input
     */
    @Test
    void testProcessPayment_ValidPayment_ReturnsProcessedPayment() {
        Payment payment = new Payment();
        Payment processed = new Payment();
        when(paymentService.processPayment(payment)).thenReturn(processed);

        ResponseEntity<Payment> response = paymentController.processPayment(payment);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(processed, response.getBody());
    }

    /**
     * Test processPayment with null payment (error scenario)
     */
    @Test
    void testProcessPayment_NullPayment_ReturnsNull() {
        when(paymentService.processPayment(null)).thenReturn(null);

        ResponseEntity<Payment> response = paymentController.processPayment(null);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
