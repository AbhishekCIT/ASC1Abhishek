package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightController.
 */
class FlightControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("searchFlights endpoint")
    class SearchFlightsTests {
        @Test
        @DisplayName("Should return flights for valid search parameters")
        void testSearchFlightsReturnsFlights() {
            // Purpose: Normal scenario - flights found
            List<Flight> flights = Arrays.asList(new Flight(), new Flight());
            when(flightSearchService.searchFlights(eq("JFK"), eq("LAX"), any(LocalDate.class))).thenReturn(flights);
            Map<String, String> params = new HashMap<>();
            params.put("origin", "JFK");
            params.put("destination", "LAX");
            params.put("date", "2025-10-01");
            ResponseEntity<List<Flight>> response = flightController.searchFlights(params);
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(2, response.getBody().size());
        }

        @Test
        @DisplayName("Should return NOT_FOUND when no flights are available")
        void testSearchFlightsReturnsNotFound() {
            // Purpose: Edge case - no flights found
            when(flightSearchService.searchFlights(anyString(), anyString(), any(LocalDate.class))).thenReturn(Collections.emptyList());
            Map<String, String> params = new HashMap<>();
            params.put("origin", "JFK");
            params.put("destination", "LAX");
            params.put("date", "2025-10-01");
            ResponseEntity<List<Flight>> response = flightController.searchFlights(params);
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertTrue(response.getBody().isEmpty());
        }

        @Test
        @DisplayName("Should throw exception for invalid date format")
        void testSearchFlightsInvalidDate() {
            // Purpose: Error-handling - invalid date format
            Map<String, String> params = new HashMap<>();
            params.put("origin", "JFK");
            params.put("destination", "LAX");
            params.put("date", "invalid-date");
            assertThrows(Exception.class, () -> flightController.searchFlights(params));
        }
    }

    @Nested
    @DisplayName("bookTicket endpoint")
    class BookTicketTests {
        @Test
        @DisplayName("Should return OK for successful booking")
        void testBookTicketSuccess() {
            // Purpose: Normal scenario - booking confirmed
            BookingRequest request = new BookingRequest();
            request.setFlightId(1L);
            request.setPassengerDetails(new Passenger());
            request.setSeat("12A");
            request.setPaymentInfo(new PaymentInfo());
            BookingResponse response = new BookingResponse(123L, "CONFIRMED", true, null);
            when(bookingService.createBooking(anyLong(), any(Passenger.class), anyString(), any(PaymentInfo.class))).thenReturn(response);
            ResponseEntity<BookingResponse> result = flightController.bookTicket(request);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals("CONFIRMED", result.getBody().getStatus());
        }

        @Test
        @DisplayName("Should return BAD_REQUEST for failed booking")
        void testBookTicketFailure() {
            // Purpose: Edge case - booking failed
            BookingRequest request = new BookingRequest();
            request.setFlightId(1L);
            request.setPassengerDetails(new Passenger());
            request.setSeat("12A");
            request.setPaymentInfo(new PaymentInfo());
            BookingResponse response = new BookingResponse(null, "FAILED", false, "Payment failed");
            when(bookingService.createBooking(anyLong(), any(Passenger.class), anyString(), any(PaymentInfo.class))).thenReturn(response);
            ResponseEntity<BookingResponse> result = flightController.bookTicket(request);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
            assertEquals("FAILED", result.getBody().getStatus());
        }

        @Test
        @DisplayName("Should handle IllegalArgumentException and return BAD_REQUEST")
        void testBookTicketIllegalArgument() {
            // Purpose: Error-handling - invalid input triggers exception
            BookingRequest request = new BookingRequest();
            request.setFlightId(1L);
            request.setPassengerDetails(new Passenger());
            request.setSeat("12A");
            request.setPaymentInfo(new PaymentInfo());
            when(bookingService.createBooking(anyLong(), any(Passenger.class), anyString(), any(PaymentInfo.class)))
                    .thenThrow(new IllegalArgumentException("Invalid details"));
            ResponseEntity<BookingResponse> result = flightController.bookTicket(request);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
            assertEquals("FAILED", result.getBody().getStatus());
            assertEquals("Invalid details", result.getBody().getErrorMessage());
        }
    }
}
