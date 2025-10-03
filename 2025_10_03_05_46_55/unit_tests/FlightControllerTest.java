package com.asc.airbooking.controller;

import com.asc.airbooking.model.*;
import com.asc.airbooking.service.FlightService;
import com.asc.airbooking.service.PaymentService;
import com.asc.airbooking.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightController.
 * Covers normal, edge, boundary, and error scenarios for all controller methods.
 */
class FlightControllerTest {

    @Mock
    private FlightService flightService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private AuthService authService;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Purpose: Test successful flight search with valid authentication and request.
     */
    @Test
    void testSearchFlights_Success() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        when(authService.authenticate(authentication)).thenReturn(true);
        when(flightService.findFlights(request)).thenReturn(response);

        ResponseEntity<FlightSearchResponse> result = flightController.searchFlights(request, authentication);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
    }

    /**
     * Purpose: Test flight search with failed authentication (should return 401).
     */
    @Test
    void testSearchFlights_Unauthorized() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(authService.authenticate(authentication)).thenReturn(false);

        ResponseEntity<FlightSearchResponse> result = flightController.searchFlights(request, authentication);
        assertEquals(401, result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    /**
     * Purpose: Test booking a flight successfully with valid authentication.
     */
    @Test
    void testBookFlight_Success() {
        BookFlightRequest request = new BookFlightRequest();
        BookFlightResponse response = new BookFlightResponse();
        when(authService.authenticate(authentication)).thenReturn(true);
        when(flightService.bookFlight(request)).thenReturn(response);

        ResponseEntity<BookFlightResponse> result = flightController.bookFlight(request, authentication);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
    }

    /**
     * Purpose: Test booking a flight with failed authentication (should return 401).
     */
    @Test
    void testBookFlight_Unauthorized() {
        BookFlightRequest request = new BookFlightRequest();
        when(authService.authenticate(authentication)).thenReturn(false);

        ResponseEntity<BookFlightResponse> result = flightController.bookFlight(request, authentication);
        assertEquals(401, result.getStatusCodeValue());
        assertNull(result.getBody());
    }

    /**
     * Purpose: Test successful payment processing.
     */
    @Test
    void testProcessPayment_Success() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setToken("tok_abc");
        when(authService.authenticate(authentication)).thenReturn(true);
        when(paymentService.processPayment(100.0, "tok_abc")).thenReturn(true);

        ResponseEntity<PaymentResponse> result = flightController.processPayment(request, authentication);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("success", result.getBody().getPaymentStatus());
        assertNotNull(result.getBody().getTransactionId());
    }

    /**
     * Purpose: Test failed payment processing (paymentService returns false).
     */
    @Test
    void testProcessPayment_Failure() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setToken("tok_abc");
        when(authService.authenticate(authentication)).thenReturn(true);
        when(paymentService.processPayment(100.0, "tok_abc")).thenReturn(false);

        ResponseEntity<PaymentResponse> result = flightController.processPayment(request, authentication);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals("failed", result.getBody().getPaymentStatus());
        assertNull(result.getBody().getTransactionId());
    }

    /**
     * Purpose: Test payment processing with failed authentication (should return 401).
     */
    @Test
    void testProcessPayment_Unauthorized() {
        PaymentRequest request = new PaymentRequest();
        when(authService.authenticate(authentication)).thenReturn(false);

        ResponseEntity<PaymentResponse> result = flightController.processPayment(request, authentication);
        assertEquals(401, result.getStatusCodeValue());
        assertNull(result.getBody());
    }
}
