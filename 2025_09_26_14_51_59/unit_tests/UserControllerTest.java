package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserController.
 */
class UserControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searching flights with valid parameters.
     */
    @Test
    @DisplayName("searchFlights returns flights for valid input")
    void testSearchFlights_ValidInput() {
        Flight flight = new Flight();
        when(flightSearchService.searchFlights("JFK", "LAX", "2024-07-01")).thenReturn(Arrays.asList(flight));
        ResponseEntity<List<Flight>> response = userController.searchFlights("JFK", "LAX", "2024-07-01");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    /**
     * Test searching flights when no flights are found.
     */
    @Test
    @DisplayName("searchFlights returns empty list when no flights found")
    void testSearchFlights_NoFlights() {
        when(flightSearchService.searchFlights(anyString(), anyString(), anyString())).thenReturn(Collections.emptyList());
        ResponseEntity<List<Flight>> response = userController.searchFlights("JFK", "LAX", "2024-07-01");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test booking a flight with valid request.
     */
    @Test
    @DisplayName("bookFlight returns confirmation for valid booking request")
    void testBookFlight_ValidRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(1L);
        confirmation.setStatus("CONFIRMED");
        confirmation.setTicket("TKT123");
        when(bookingService.createBooking(bookingRequest)).thenReturn(confirmation);
        ResponseEntity<BookingConfirmation> response = userController.bookFlight(bookingRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("CONFIRMED", response.getBody().getStatus());
    }

    /**
     * Test booking a flight when service throws exception.
     */
    @Test
    @DisplayName("bookFlight throws exception for invalid booking request")
    void testBookFlight_InvalidRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        when(bookingService.createBooking(bookingRequest)).thenThrow(new IllegalArgumentException("Invalid request"));
        assertThrows(IllegalArgumentException.class, () -> userController.bookFlight(bookingRequest));
    }

    /**
     * Test processing payment with valid request.
     */
    @Test
    @DisplayName("processPayment returns response for valid payment request")
    void testProcessPayment_ValidRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentStatus("SUCCESS");
        paymentResponse.setTransactionId("TXN123");
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);
        ResponseEntity<PaymentResponse> response = userController.processPayment(paymentRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("SUCCESS", response.getBody().getPaymentStatus());
    }

    /**
     * Test processing payment when service throws exception.
     */
    @Test
    @DisplayName("processPayment throws exception for invalid payment request")
    void testProcessPayment_InvalidRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        when(paymentService.processPayment(paymentRequest)).thenThrow(new IllegalArgumentException("Invalid payment"));
        assertThrows(IllegalArgumentException.class, () -> userController.processPayment(paymentRequest));
    }

    /**
     * Test getting booking details with valid booking ID.
     */
    @Test
    @DisplayName("getBooking returns confirmation for valid booking ID")
    void testGetBooking_ValidId() {
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(1L);
        confirmation.setStatus("CONFIRMED");
        confirmation.setTicket("TKT123");
        when(bookingService.getBooking(1L)).thenReturn(confirmation);
        ResponseEntity<BookingConfirmation> response = userController.getBooking(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getBookingId());
    }

    /**
     * Test getting booking details when booking is not found.
     */
    @Test
    @DisplayName("getBooking throws exception for invalid booking ID")
    void testGetBooking_InvalidId() {
        when(bookingService.getBooking(99L)).thenThrow(new RuntimeException("Booking not found"));
        assertThrows(RuntimeException.class, () -> userController.getBooking(99L));
    }
}
