package com.airtransport.controller;

import com.airtransport.model.Booking;
import com.airtransport.model.Payment;
import com.airtransport.service.BookingService;
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
 * Unit tests for BookingController.
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
     * Test successful booking and payment scenario.
     */
    @Test
    void testBookFlight_Success() {
        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        request.setUserId("U456");
        request.setPassengerInfo(new HashMap<>());
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        request.setPaymentDetails(paymentDetails);

        Booking booking = new Booking();
        booking.setBookingId("B789");
        booking.setStatus("PENDING_PAYMENT");
        booking.setTicketNumber("T0001");
        Payment payment = new Payment();
        payment.setStatus("SUCCESS");

        when(bookingService.createBooking(anyString(), anyString(), anyMap())).thenReturn(booking);
        when(paymentService.processPayment(anyString(), anyMap())).thenReturn(payment);

        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingController.BookingResponse);
        BookingController.BookingResponse resp = (BookingController.BookingResponse) response.getBody();
        assertEquals("B789", resp.getBookingId());
        assertEquals("CONFIRMED", resp.getStatus());
        assertEquals("T0001", resp.getTicketNumber());
    }

    /**
     * Test booking with payment failure scenario.
     */
    @Test
    void testBookFlight_PaymentFailure() {
        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        request.setUserId("U456");
        request.setPassengerInfo(new HashMap<>());
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("cardNumber", "1234567890123456");
        request.setPaymentDetails(paymentDetails);

        Booking booking = new Booking();
        booking.setBookingId("B789");
        booking.setStatus("PENDING_PAYMENT");
        booking.setTicketNumber("T0001");
        Payment payment = new Payment();
        payment.setStatus("FAILED");

        when(bookingService.createBooking(anyString(), anyString(), anyMap())).thenReturn(booking);
        when(paymentService.processPayment(anyString(), anyMap())).thenReturn(payment);

        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingController.ErrorResponse);
        BookingController.ErrorResponse resp = (BookingController.ErrorResponse) response.getBody();
        assertEquals("Payment failed.", resp.getError());
    }

    /**
     * Test booking with invalid arguments (IllegalArgumentException).
     */
    @Test
    void testBookFlight_InvalidArgument() {
        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId(null);
        request.setUserId("U456");
        request.setPassengerInfo(new HashMap<>());
        request.setPaymentDetails(new HashMap<>());

        when(bookingService.createBooking(anyString(), anyString(), anyMap())).thenThrow(new IllegalArgumentException("Invalid flight selection."));

        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingController.ErrorResponse);
        BookingController.ErrorResponse resp = (BookingController.ErrorResponse) response.getBody();
        assertEquals("Invalid flight selection.", resp.getError());
    }

    /**
     * Test booking with unexpected exception (internal server error).
     */
    @Test
    void testBookFlight_InternalServerError() {
        BookingController.BookingRequest request = new BookingController.BookingRequest();
        request.setFlightId("F123");
        request.setUserId("U456");
        request.setPassengerInfo(new HashMap<>());
        request.setPaymentDetails(new HashMap<>());

        when(bookingService.createBooking(anyString(), anyString(), anyMap())).thenThrow(new RuntimeException("DB error"));

        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingController.ErrorResponse);
        BookingController.ErrorResponse resp = (BookingController.ErrorResponse) response.getBody();
        assertEquals("Internal server error.", resp.getError());
    }
}
