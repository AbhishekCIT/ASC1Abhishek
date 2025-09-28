package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.model.BookingResponse;
import com.example.airlinebooking.model.PaymentCallbackRequest;
import com.example.airlinebooking.model.PaymentCallbackResponse;
import com.example.airlinebooking.service.BookingService;
import com.example.airlinebooking.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for BookingController.
 * Covers normal, edge, and error scenarios for booking and payment callback endpoints.
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
     * Test booking a flight successfully.
     */
    @Test
    void testBookFlight_Success() {
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();
        response.setBookingReference("ABC123");
        response.setStatus("CONFIRMED");
        when(bookingService.createBooking(request)).thenReturn(response);
        ResponseEntity<BookingResponse> result = bookingController.bookFlight(request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("ABC123", result.getBody().getBookingReference());
        assertEquals("CONFIRMED", result.getBody().getStatus());
    }

    /**
     * Test booking a flight when service throws exception.
     */
    @Test
    void testBookFlight_ServiceThrowsException() {
        BookingRequest request = new BookingRequest();
        when(bookingService.createBooking(request)).thenThrow(new RuntimeException("Error"));
        assertThrows(RuntimeException.class, () -> bookingController.bookFlight(request));
    }

    /**
     * Test payment callback success.
     */
    @Test
    void testPaymentCallback_Success() {
        PaymentCallbackRequest request = new PaymentCallbackRequest();
        PaymentCallbackResponse response = new PaymentCallbackResponse();
        response.setPaymentStatus("SUCCESS");
        when(paymentService.handlePaymentCallback(request)).thenReturn(response);
        ResponseEntity<PaymentCallbackResponse> result = bookingController.paymentCallback(request);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("SUCCESS", result.getBody().getPaymentStatus());
    }

    /**
     * Test payment callback when service throws exception.
     */
    @Test
    void testPaymentCallback_ServiceThrowsException() {
        PaymentCallbackRequest request = new PaymentCallbackRequest();
        when(paymentService.handlePaymentCallback(request)).thenThrow(new RuntimeException("Callback error"));
        assertThrows(RuntimeException.class, () -> bookingController.paymentCallback(request));
    }
}
