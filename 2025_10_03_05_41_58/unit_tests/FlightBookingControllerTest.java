package com.airtransport.controller;

import com.airtransport.entity.Flight;
import com.airtransport.model.*;
import com.airtransport.service.BookingService;
import com.airtransport.service.FlightSearchService;
import com.airtransport.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightBookingController.
 * Covers normal, edge, and error scenarios for all endpoints.
 */
class FlightBookingControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private FlightBookingController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("searchFlights")
    class SearchFlightsTests {
        @Test
        @DisplayName("Should return available flights for valid search request")
        void testSearchFlightsNormal() {
            FlightSearchRequest req = new FlightSearchRequest("2025-10-10", "LAX", "Delta");
            Flight flight = new Flight("DL123", "Delta", "JFK", "LAX", LocalDateTime.now(), LocalDateTime.now().plusHours(5), BigDecimal.valueOf(350));
            when(flightSearchService.searchFlights(anyString(), anyString(), anyString())).thenReturn(List.of(flight));

            ResponseEntity<FlightSearchResponse> resp = controller.searchFlights(req);
            assertEquals(HttpStatus.OK, resp.getStatusCode());
            assertNotNull(resp.getBody());
            assertEquals(1, resp.getBody().getFlights().size());
        }

        @Test
        @DisplayName("Should return empty list if no flights found")
        void testSearchFlightsNoResults() {
            FlightSearchRequest req = new FlightSearchRequest("2025-10-10", "LAX", "Delta");
            when(flightSearchService.searchFlights(anyString(), anyString(), anyString())).thenReturn(Collections.emptyList());
            ResponseEntity<FlightSearchResponse> resp = controller.searchFlights(req);
            assertEquals(HttpStatus.OK, resp.getStatusCode());
            assertNotNull(resp.getBody());
            assertTrue(resp.getBody().getFlights().isEmpty());
        }
    }

    @Nested
    @DisplayName("bookFlight")
    class BookFlightTests {
        @Test
        @DisplayName("Should create booking and return response for valid request")
        void testBookFlightNormal() {
            BookingRequest req = new BookingRequest("DL123", null, null);
            BookingResponse bookingResp = new BookingResponse("BK123", "CONFIRMED", null);
            when(bookingService.bookFlight(any())).thenReturn(bookingResp);
            ResponseEntity<BookingResponse> resp = controller.bookFlight(req);
            assertEquals(HttpStatus.CREATED, resp.getStatusCode());
            assertEquals("BK123", resp.getBody().getBookingId());
        }

        @Test
        @DisplayName("Should handle booking service exception")
        void testBookFlightError() {
            BookingRequest req = new BookingRequest("DL123", null, null);
            when(bookingService.bookFlight(any())).thenThrow(new RuntimeException("Booking failed"));
            assertThrows(RuntimeException.class, () -> controller.bookFlight(req));
        }
    }

    @Nested
    @DisplayName("processPayment")
    class ProcessPaymentTests {
        @Test
        @DisplayName("Should process payment and return success response")
        void testProcessPaymentNormal() {
            PaymentRequest req = new PaymentRequest("BK123", 350.0, "stripe");
            when(paymentService.processPayment(anyString(), anyDouble(), anyString())).thenReturn("TXN456");
            ResponseEntity<PaymentResponse> resp = controller.processPayment(req);
            assertEquals(HttpStatus.OK, resp.getStatusCode());
            assertEquals("SUCCESS", resp.getBody().getPaymentStatus());
            assertEquals("TXN456", resp.getBody().getTransactionId());
        }

        @Test
        @DisplayName("Should handle payment service failure")
        void testProcessPaymentError() {
            PaymentRequest req = new PaymentRequest("BK123", 350.0, "stripe");
            when(paymentService.processPayment(anyString(), anyDouble(), anyString())).thenThrow(new RuntimeException("Payment failed"));
            assertThrows(RuntimeException.class, () -> controller.processPayment(req));
        }
    }

    @Nested
    @DisplayName("getBookingConfirmation")
    class GetBookingConfirmationTests {
        @Test
        @DisplayName("Should return booking confirmation for valid bookingId")
        void testGetBookingConfirmationNormal() {
            String bookingId = "BK123";
            ResponseEntity<BookingConfirmationResponse> resp = controller.getBookingConfirmation(bookingId);
            assertEquals(HttpStatus.OK, resp.getStatusCode());
            assertNotNull(resp.getBody());
            assertEquals(bookingId, resp.getBody().getBookingId());
            assertEquals("CONFIRMED", resp.getBody().getStatus());
        }
    }
}
