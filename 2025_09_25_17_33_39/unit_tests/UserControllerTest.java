package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import com.example.airbooking.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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
    private JwtAuthenticationToken authentication;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("searchFlights() tests")
    class SearchFlightsTests {
        @Test
        @DisplayName("Should return list of flights for valid search criteria")
        void testSearchFlightsSuccess() {
            // Arrange
            List<FlightResponse> flights = Arrays.asList(
                    new FlightResponse(1L, "Delta", 200.0, 5),
                    new FlightResponse(2L, "United", 250.0, 3)
            );
            when(flightSearchService.searchFlights("JFK", "LAX", "2025-10-01", 2)).thenReturn(flights);

            // Act
            ResponseEntity<List<FlightResponse>> response = userController.searchFlights("JFK", "LAX", "2025-10-01", 2);

            // Assert
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(flights, response.getBody());
        }

        @Test
        @DisplayName("Should return empty list if no flights found")
        void testSearchFlightsNoResults() {
            // Arrange
            when(flightSearchService.searchFlights(anyString(), anyString(), anyString(), anyInt())).thenReturn(Collections.emptyList());

            // Act
            ResponseEntity<List<FlightResponse>> response = userController.searchFlights("JFK", "LAX", "2025-10-01", 2);

            // Assert
            assertEquals(200, response.getStatusCodeValue());
            assertNotNull(response.getBody());
            assertTrue(response.getBody().isEmpty());
        }
    }

    @Nested
    @DisplayName("bookFlight() tests")
    class BookFlightTests {
        private BookingRequest bookingRequest;
        private BookingConfirmation confirmation;

        @BeforeEach
        void setupBooking() {
            bookingRequest = new BookingRequest();
            bookingRequest.setFlightId(123L);
            bookingRequest.setUserId(456L);
            confirmation = new BookingConfirmation();
            confirmation.setBookingId(789L);
            confirmation.setStatus("CONFIRMED");
        }

        @Test
        @DisplayName("Should return confirmation for successful booking")
        void testBookFlightSuccess() {
            // Arrange
            when(bookingService.bookFlight(any(BookingRequest.class), any(JwtAuthenticationToken.class))).thenReturn(confirmation);

            // Act
            ResponseEntity<?> response = userController.bookFlight(bookingRequest, authentication);

            // Assert
            assertEquals(200, response.getStatusCodeValue());
            assertTrue(response.getBody() instanceof BookingConfirmation);
            assertEquals(confirmation, response.getBody());
        }

        @Test
        @DisplayName("Should return error response when no seats available")
        void testBookFlightNoSeats() {
            // Arrange
            when(bookingService.bookFlight(any(BookingRequest.class), any(JwtAuthenticationToken.class)))
                    .thenThrow(new NoSeatsAvailableException("No seats available for selected flight."));

            // Act
            ResponseEntity<?> response = userController.bookFlight(bookingRequest, authentication);

            // Assert
            assertEquals(400, response.getStatusCodeValue());
            assertTrue(response.getBody() instanceof ErrorResponse);
            ErrorResponse error = (ErrorResponse) response.getBody();
            assertEquals("NO_SEATS", error.getErrorCode());
        }

        @Test
        @DisplayName("Should return error response when payment fails")
        void testBookFlightPaymentFailed() {
            // Arrange
            when(bookingService.bookFlight(any(BookingRequest.class), any(JwtAuthenticationToken.class)))
                    .thenThrow(new PaymentFailedException("Payment processing failed."));

            // Act
            ResponseEntity<?> response = userController.bookFlight(bookingRequest, authentication);

            // Assert
            assertEquals(400, response.getStatusCodeValue());
            assertTrue(response.getBody() instanceof ErrorResponse);
            ErrorResponse error = (ErrorResponse) response.getBody();
            assertEquals("PAYMENT_FAILED", error.getErrorCode());
        }

        @Test
        @DisplayName("Should return error response when airline API fails")
        void testBookFlightAirlineApiError() {
            // Arrange
            when(bookingService.bookFlight(any(BookingRequest.class), any(JwtAuthenticationToken.class)))
                    .thenThrow(new AirlineApiException("Airline API error."));

            // Act
            ResponseEntity<?> response = userController.bookFlight(bookingRequest, authentication);

            // Assert
            assertEquals(502, response.getStatusCodeValue());
            assertTrue(response.getBody() instanceof ErrorResponse);
            ErrorResponse error = (ErrorResponse) response.getBody();
            assertEquals("AIRLINE_API_ERROR", error.getErrorCode());
        }

        @Test
        @DisplayName("Should return internal error for unexpected exception")
        void testBookFlightUnexpectedError() {
            // Arrange
            when(bookingService.bookFlight(any(BookingRequest.class), any(JwtAuthenticationToken.class)))
                    .thenThrow(new RuntimeException("Unexpected error"));

            // Act
            ResponseEntity<?> response = userController.bookFlight(bookingRequest, authentication);

            // Assert
            assertEquals(500, response.getStatusCodeValue());
            assertTrue(response.getBody() instanceof ErrorResponse);
            ErrorResponse error = (ErrorResponse) response.getBody();
            assertEquals("INTERNAL_ERROR", error.getErrorCode());
        }
    }
}
