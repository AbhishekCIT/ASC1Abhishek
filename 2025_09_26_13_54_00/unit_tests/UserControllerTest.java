package com.airtransport.controller;

import com.airtransport.model.*;
import com.airtransport.service.*;
import com.airtransport.exception.*;
import org.junit.jupiter.api.BeforeEach;
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
public class UserControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searching flights with valid parameters returns a list of flights.
     */
    @Test
    void testSearchFlights_ReturnsFlightsList() {
        FlightResponse flight1 = new FlightResponse(1L, "Delta", "JFK", "LAX", "2025-10-01T10:00", 300.0);
        FlightResponse flight2 = new FlightResponse(2L, "United", "JFK", "LAX", "2025-10-01T12:00", 320.0);
        when(flightSearchService.searchFlights("JFK", "LAX", "2025-10-01", null))
                .thenReturn(Arrays.asList(flight1, flight2));

        ResponseEntity<List<FlightResponse>> response = userController.searchFlights("JFK", "LAX", "2025-10-01", null);
        assertEquals(2, response.getBody().size());
        assertEquals("Delta", response.getBody().get(0).getAirline());
    }

    /**
     * Test searching flights returns empty list when no flights found.
     */
    @Test
    void testSearchFlights_ReturnsEmptyList() {
        when(flightSearchService.searchFlights(anyString(), anyString(), anyString(), any()))
                .thenReturn(Collections.emptyList());
        ResponseEntity<List<FlightResponse>> response = userController.searchFlights("JFK", "LAX", "2025-10-01", null);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test booking a flight successfully returns confirmation.
     */
    @Test
    void testBookFlight_Success() {
        BookingRequest request = new BookingRequest(1L, 123L, "Aisle", new PaymentInfo("4111111111111111", "12/25", "123"));
        BookingConfirmation confirmation = new BookingConfirmation(456L, "CONFIRMED", "XYZ123", true);
        when(bookingService.bookFlight(request)).thenReturn(confirmation);

        ResponseEntity<?> response = userController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingConfirmation);
        assertEquals(456L, ((BookingConfirmation) response.getBody()).getBookingId());
    }

    /**
     * Test booking a flight with payment failure returns error response.
     */
    @Test
    void testBookFlight_PaymentFailed() {
        BookingRequest request = new BookingRequest(1L, 123L, "Aisle", new PaymentInfo("4000000000000002", "12/25", "123"));
        when(bookingService.bookFlight(request)).thenThrow(new PaymentFailedException("Payment declined"));

        ResponseEntity<?> response = userController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("PaymentFailedException", error.getErrorCode());
        assertEquals("Payment declined", error.getMessage());
    }

    /**
     * Test booking a flight with seat unavailable returns error response.
     */
    @Test
    void testBookFlight_SeatUnavailable() {
        BookingRequest request = new BookingRequest(1L, 123L, "Aisle", new PaymentInfo("4111111111111111", "12/25", "123"));
        when(bookingService.bookFlight(request)).thenThrow(new SeatUnavailableException("Seat not available"));

        ResponseEntity<?> response = userController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("SeatUnavailableException", error.getErrorCode());
        assertEquals("Seat not available", error.getMessage());
    }

    /**
     * Test getting booking confirmation successfully.
     */
    @Test
    void testGetConfirmation_Success() {
        BookingConfirmation confirmation = new BookingConfirmation(456L, "CONFIRMED", "XYZ123", true);
        when(bookingService.getConfirmation(456L)).thenReturn(confirmation);

        ResponseEntity<?> response = userController.getConfirmation(456L);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof BookingConfirmation);
        assertEquals(456L, ((BookingConfirmation) response.getBody()).getBookingId());
    }

    /**
     * Test getting booking confirmation for non-existent booking returns error.
     */
    @Test
    void testGetConfirmation_NotFound() {
        when(bookingService.getConfirmation(999L)).thenThrow(new NotFoundException("Booking not found"));

        ResponseEntity<?> response = userController.getConfirmation(999L);
        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse error = (ErrorResponse) response.getBody();
        assertEquals("NotFoundException", error.getErrorCode());
        assertEquals("Booking not found", error.getMessage());
    }
}
