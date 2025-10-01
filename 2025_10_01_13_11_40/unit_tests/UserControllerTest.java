package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.*;
import com.example.airlinebooking.service.FlightService;
import com.example.airlinebooking.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserController.
 */
class UserControllerTest {
    @Mock
    private FlightService flightService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario for searching flights.
     */
    @Test
    @DisplayName("searchFlights returns list of flights for valid request")
    void testSearchFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest("DEL", "BLR", "2025-10-15");
        List<FlightSearchResponse> mockResponse = List.of(new FlightSearchResponse());
        when(flightService.searchFlights(any())).thenReturn(mockResponse);
        ResponseEntity<List<FlightSearchResponse>> response = userController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test edge case: empty origin or destination.
     */
    @Test
    @DisplayName("searchFlights with empty origin throws exception")
    void testSearchFlights_EmptyOrigin() {
        FlightSearchRequest request = new FlightSearchRequest("", "BLR", "2025-10-15");
        when(flightService.searchFlights(any())).thenThrow(new IllegalArgumentException("Origin cannot be empty"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> userController.searchFlights(request));
        assertEquals("Origin cannot be empty", ex.getMessage());
    }

    /**
     * Test normal scenario for viewing flight details.
     */
    @Test
    @DisplayName("viewFlight returns flight details for valid flightId")
    void testViewFlight_Normal() {
        String flightId = "AI123";
        Map<String, Object> mockDetails = Map.of("flightId", flightId);
        when(flightService.viewFlight(flightId)).thenReturn(mockDetails);
        ResponseEntity<Map<String, Object>> response = userController.viewFlight(flightId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockDetails, response.getBody());
    }

    /**
     * Test edge case: invalid flightId.
     */
    @Test
    @DisplayName("viewFlight with invalid flightId throws exception")
    void testViewFlight_InvalidFlightId() {
        String flightId = "INVALID";
        when(flightService.viewFlight(flightId)).thenThrow(new NoSuchElementException("Flight not found"));
        Exception ex = assertThrows(NoSuchElementException.class, () -> userController.viewFlight(flightId));
        assertEquals("Flight not found", ex.getMessage());
    }

    /**
     * Test normal scenario for booking a ticket.
     */
    @Test
    @DisplayName("bookTicket returns confirmation for valid request")
    void testBookTicket_Normal() {
        BookTicketRequest request = new BookTicketRequest();
        BookTicketResponse mockResponse = new BookTicketResponse();
        when(flightService.bookTicket(any())).thenReturn(mockResponse);
        ResponseEntity<BookTicketResponse> response = userController.bookTicket(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test error scenario: invalid seat selection.
     */
    @Test
    @DisplayName("bookTicket with invalid seat throws exception")
    void testBookTicket_InvalidSeat() {
        BookTicketRequest request = new BookTicketRequest();
        when(flightService.bookTicket(any())).thenThrow(new IllegalArgumentException("Invalid seat selection"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> userController.bookTicket(request));
        assertEquals("Invalid seat selection", ex.getMessage());
    }

    /**
     * Test normal scenario for processing payment.
     */
    @Test
    @DisplayName("processPayment returns success for valid payment")
    void testProcessPayment_Normal() {
        PaymentRequest request = new PaymentRequest();
        PaymentResponse mockResponse = new PaymentResponse();
        when(paymentService.processPayment(any())).thenReturn(mockResponse);
        ResponseEntity<PaymentResponse> response = userController.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test error scenario: payment failure.
     */
    @Test
    @DisplayName("processPayment with invalid details throws exception")
    void testProcessPayment_InvalidDetails() {
        PaymentRequest request = new PaymentRequest();
        when(paymentService.processPayment(any())).thenThrow(new IllegalArgumentException("Payment failed/invalid details"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> userController.processPayment(request));
        assertEquals("Payment failed/invalid details", ex.getMessage());
    }

    /**
     * Test normal scenario for booking confirmation.
     */
    @Test
    @DisplayName("getBookingConfirmation returns confirmation for valid bookingId")
    void testGetBookingConfirmation_Normal() {
        String bookingId = "B123";
        BookingConfirmationResponse mockResponse = new BookingConfirmationResponse();
        when(flightService.getBookingConfirmation(bookingId)).thenReturn(mockResponse);
        ResponseEntity<BookingConfirmationResponse> response = userController.getBookingConfirmation(bookingId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test error scenario: booking not found.
     */
    @Test
    @DisplayName("getBookingConfirmation with invalid bookingId throws exception")
    void testGetBookingConfirmation_NotFound() {
        String bookingId = "INVALID";
        when(flightService.getBookingConfirmation(bookingId)).thenThrow(new NoSuchElementException("Booking not found"));
        Exception ex = assertThrows(NoSuchElementException.class, () -> userController.getBookingConfirmation(bookingId));
        assertEquals("Booking not found", ex.getMessage());
    }
}
