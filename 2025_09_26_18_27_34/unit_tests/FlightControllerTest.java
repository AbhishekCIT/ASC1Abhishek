package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.BookingService;
import com.example.flightbooking.service.FlightSearchService;
import com.example.flightbooking.service.PaymentService;
import com.example.flightbooking.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController.
 */
class FlightControllerTest {

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private FlightController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test searchFlights - normal scenario")
    void testSearchFlights_Success() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        List<Flight> flights = Arrays.asList(flight1, flight2);
        doNothing().when(validationUtil).validateSearchCriteria(criteria);
        when(flightSearchService.searchFlights(criteria)).thenReturn(flights);

        // Act
        ResponseEntity<?> response = controller.searchFlights(criteria);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof FlightSearchResponse);
        FlightSearchResponse resp = (FlightSearchResponse) response.getBody();
        assertEquals(2, resp.getFlights().size());
    }

    @Test
    @DisplayName("Test searchFlights - validation error")
    void testSearchFlights_ValidationError() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        doThrow(new IllegalArgumentException("Invalid criteria")).when(validationUtil).validateSearchCriteria(criteria);

        // Act
        ResponseEntity<?> response = controller.searchFlights(criteria);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid criteria", response.getBody());
    }

    @Test
    @DisplayName("Test searchFlights - no flights found (empty list)")
    void testSearchFlights_EmptyList() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        doNothing().when(validationUtil).validateSearchCriteria(criteria);
        when(flightSearchService.searchFlights(criteria)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = controller.searchFlights(criteria);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        FlightSearchResponse resp = (FlightSearchResponse) response.getBody();
        assertNotNull(resp);
        assertTrue(resp.getFlights().isEmpty());
    }

    @Test
    @DisplayName("Test bookFlight - normal scenario")
    void testBookFlight_Success() {
        // Arrange
        BookingRequest request = new BookingRequest();
        Booking booking = new Booking();
        doNothing().when(validationUtil).validateBookingRequest(request);
        when(bookingService.bookFlight(request, "Bearer token")).thenReturn(booking);

        // Act
        ResponseEntity<?> response = controller.bookFlight(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof BookingResponse);
        BookingResponse resp = (BookingResponse) response.getBody();
        assertSame(booking, resp.getBooking());
    }

    @Test
    @DisplayName("Test bookFlight - validation error")
    void testBookFlight_ValidationError() {
        // Arrange
        BookingRequest request = new BookingRequest();
        doThrow(new IllegalArgumentException("Invalid booking")).when(validationUtil).validateBookingRequest(request);

        // Act
        ResponseEntity<?> response = controller.bookFlight(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid booking", response.getBody());
    }

    @Test
    @DisplayName("Test bookFlight - booking service error")
    void testBookFlight_ServiceError() {
        // Arrange
        BookingRequest request = new BookingRequest();
        doNothing().when(validationUtil).validateBookingRequest(request);
        when(bookingService.bookFlight(request, "Bearer token")).thenThrow(new RuntimeException("Booking failed"));

        // Act
        ResponseEntity<?> response = controller.bookFlight(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Booking failed", response.getBody());
    }

    @Test
    @DisplayName("Test processPayment - normal scenario")
    void testProcessPayment_Success() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        PaymentResult result = new PaymentResult();
        doNothing().when(validationUtil).validatePaymentRequest(request);
        when(paymentService.processPayment(request)).thenReturn(result);

        // Act
        ResponseEntity<?> response = controller.processPayment(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(result, response.getBody());
    }

    @Test
    @DisplayName("Test processPayment - validation error")
    void testProcessPayment_ValidationError() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        doThrow(new IllegalArgumentException("Invalid payment")).when(validationUtil).validatePaymentRequest(request);

        // Act
        ResponseEntity<?> response = controller.processPayment(request);

        // Assert
        assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());
        assertEquals("Invalid payment", response.getBody());
    }

    @Test
    @DisplayName("Test processPayment - payment service error")
    void testProcessPayment_ServiceError() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        doNothing().when(validationUtil).validatePaymentRequest(request);
        when(paymentService.processPayment(request)).thenThrow(new RuntimeException("Payment failed"));

        // Act
        ResponseEntity<?> response = controller.processPayment(request);

        // Assert
        assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());
        assertEquals("Payment failed", response.getBody());
    }
}
