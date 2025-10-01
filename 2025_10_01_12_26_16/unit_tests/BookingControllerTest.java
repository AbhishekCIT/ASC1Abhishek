package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.model.Payment;
import com.example.airlinebooking.service.BookingService;
import com.example.airlinebooking.service.FlightSearchService;
import com.example.airlinebooking.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for all endpoints.
 */
class BookingControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
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

    // --- searchFlights ---
    @Test
    @DisplayName("searchFlights: returns flights for valid input")
    void testSearchFlightsSuccess() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.searchFlights(anyString(), anyString(), anyString())).thenReturn(flights);
        ResponseEntity<?> response = bookingController.searchFlights("2025-12-01", "NYC", "Economy");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("flights"));
        assertEquals(2, ((List<?>)body.get("flights")).size());
    }

    @Test
    @DisplayName("searchFlights: returns error for invalid input")
    void testSearchFlightsInvalidInput() {
        when(flightSearchService.searchFlights(anyString(), anyString(), anyString())).thenThrow(new IllegalArgumentException("Invalid travel date"));
        ResponseEntity<?> response = bookingController.searchFlights("invalid-date", "NYC", "Economy");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertTrue(body.containsKey("error"));
        assertEquals("Invalid travel date", body.get("error"));
    }

    // --- bookTicket ---
    @Test
    @DisplayName("bookTicket: successful booking")
    void testBookTicketSuccess() {
        Map<String, Object> request = new HashMap<>();
        request.put("flightId", "F123");
        request.put("passengerId", "P456");
        request.put("class", "Economy");
        request.put("paymentInfo", new Object());
        Booking booking = new Booking();
        booking.setBookingRef("BR789");
        booking.setStatus("CONFIRMED");
        when(bookingService.bookTicket(anyString(), anyString(), anyString(), any())).thenReturn(booking);
        ResponseEntity<?> response = bookingController.bookTicket(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("BR789", body.get("bookingRef"));
        assertEquals("CONFIRMED", body.get("status"));
    }

    @Test
    @DisplayName("bookTicket: booking fails with error")
    void testBookTicketFailure() {
        Map<String, Object> request = new HashMap<>();
        request.put("flightId", "F123");
        request.put("passengerId", "P456");
        request.put("class", "Economy");
        request.put("paymentInfo", new Object());
        when(bookingService.bookTicket(anyString(), anyString(), anyString(), any())).thenThrow(new RuntimeException("No seats available"));
        ResponseEntity<?> response = bookingController.bookTicket(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("No seats available", body.get("error"));
    }

    // --- chargePayment ---
    @Test
    @DisplayName("chargePayment: successful payment")
    void testChargePaymentSuccess() {
        Map<String, Object> request = new HashMap<>();
        request.put("amount", 250.00);
        request.put("cardDetails", new Object());
        Payment payment = new Payment();
        payment.setPaymentId("PAY001");
        payment.setStatus("SUCCESS");
        when(paymentService.charge(any(BigDecimal.class), any())).thenReturn(payment);
        ResponseEntity<?> response = bookingController.chargePayment(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("PAY001", body.get("paymentId"));
        assertEquals("SUCCESS", body.get("status"));
    }

    @Test
    @DisplayName("chargePayment: payment fails with error")
    void testChargePaymentFailure() {
        Map<String, Object> request = new HashMap<>();
        request.put("amount", 250.00);
        request.put("cardDetails", new Object());
        when(paymentService.charge(any(BigDecimal.class), any())).thenThrow(new RuntimeException("Payment failed"));
        ResponseEntity<?> response = bookingController.chargePayment(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("Payment failed", body.get("error"));
    }

    // --- getBookingConfirmation ---
    @Test
    @DisplayName("getBookingConfirmation: returns booking details for valid ref")
    void testGetBookingConfirmationSuccess() {
        Booking booking = new Booking();
        booking.setBookingRef("BR789");
        booking.setStatus("CONFIRMED");
        when(bookingService.getBookingConfirmation(anyString())).thenReturn(booking);
        ResponseEntity<?> response = bookingController.getBookingConfirmation("BR789");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("BR789", body.get("bookingRef"));
        assertEquals("CONFIRMED", body.get("status"));
        assertEquals(booking, body.get("details"));
    }

    @Test
    @DisplayName("getBookingConfirmation: returns error for invalid ref")
    void testGetBookingConfirmationNotFound() {
        when(bookingService.getBookingConfirmation(anyString())).thenThrow(new IllegalArgumentException("Invalid booking reference"));
        ResponseEntity<?> response = bookingController.getBookingConfirmation("INVALID");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Map<?,?> body = (Map<?,?>) response.getBody();
        assertNotNull(body);
        assertEquals("Invalid booking reference", body.get("error"));
    }
}
