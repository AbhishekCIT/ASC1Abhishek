package com.example.airline.controller;

import com.example.airline.dto.BookingRequest;
import com.example.airline.dto.BookingResponse;
import com.example.airline.dto.PaymentRequest;
import com.example.airline.dto.PaymentResponse;
import com.example.airline.service.BookingService;
import com.example.airline.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for booking and payment APIs.
 */
class BookingControllerTest {

    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test booking a flight with valid input (normal scenario).
     */
    @Test
    @DisplayName("bookFlight: should return BookingResponse for valid request")
    void bookFlight_validRequest_returnsBookingResponse() {
        BookingRequest request = new BookingRequest();
        BookingResponse expectedResponse = new BookingResponse();
        when(bookingService.bookFlight(request)).thenReturn(expectedResponse);

        ResponseEntity<BookingResponse> response = bookingController.bookFlight(request);

        assertNotNull(response);
        assertEquals(expectedResponse, response.getBody());
        verify(bookingService, times(1)).bookFlight(request);
    }

    /**
     * Test booking a flight with null request (edge case).
     */
    @Test
    @DisplayName("bookFlight: should throw NullPointerException for null request")
    void bookFlight_nullRequest_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            bookingController.bookFlight(null);
        });
    }

    /**
     * Test processPayment with valid input (normal scenario).
     */
    @Test
    @DisplayName("processPayment: should return PaymentResponse for valid request")
    void processPayment_validRequest_returnsPaymentResponse() {
        PaymentRequest request = new PaymentRequest();
        PaymentResponse expectedResponse = new PaymentResponse();
        when(paymentService.processPayment(request)).thenReturn(expectedResponse);

        ResponseEntity<PaymentResponse> response = bookingController.processPayment(request);

        assertNotNull(response);
        assertEquals(expectedResponse, response.getBody());
        verify(paymentService, times(1)).processPayment(request);
    }

    /**
     * Test processPayment with null request (edge case).
     */
    @Test
    @DisplayName("processPayment: should throw NullPointerException for null request")
    void processPayment_nullRequest_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            bookingController.processPayment(null);
        });
    }

    /**
     * Test booking a flight when BookingService throws exception (error-handling scenario).
     */
    @Test
    @DisplayName("bookFlight: should propagate exception from BookingService")
    void bookFlight_serviceThrowsException_propagates() {
        BookingRequest request = new BookingRequest();
        when(bookingService.bookFlight(request)).thenThrow(new RuntimeException("Service error"));
        assertThrows(RuntimeException.class, () -> bookingController.bookFlight(request));
    }

    /**
     * Test processPayment when PaymentService throws exception (error-handling scenario).
     */
    @Test
    @DisplayName("processPayment: should propagate exception from PaymentService")
    void processPayment_serviceThrowsException_propagates() {
        PaymentRequest request = new PaymentRequest();
        when(paymentService.processPayment(request)).thenThrow(new RuntimeException("Payment error"));
        assertThrows(RuntimeException.class, () -> bookingController.processPayment(request));
    }
}
