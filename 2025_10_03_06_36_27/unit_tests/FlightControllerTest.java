package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import com.example.airbooking.util.ValidationUtil;
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
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightController
 */
public class FlightControllerTest {

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario for searching flights
     */
    @Test
    void testSearchFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        List<FlightResponse> mockFlights = Arrays.asList(new FlightResponse(), new FlightResponse());
        doNothing().when(validationUtil).validateFlightSearchRequest(request);
        when(flightSearchService.searchFlights(request)).thenReturn(mockFlights);

        ResponseEntity<List<FlightResponse>> response = flightController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    /**
     * Test edge case: search returns empty list
     */
    @Test
    void testSearchFlights_EmptyResult() {
        FlightSearchRequest request = new FlightSearchRequest();
        doNothing().when(validationUtil).validateFlightSearchRequest(request);
        when(flightSearchService.searchFlights(request)).thenReturn(Collections.emptyList());

        ResponseEntity<List<FlightResponse>> response = flightController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test error scenario: invalid search request
     */
    @Test
    void testSearchFlights_InvalidRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        doThrow(new IllegalArgumentException("Invalid request")).when(validationUtil).validateFlightSearchRequest(request);
        assertThrows(IllegalArgumentException.class, () -> flightController.searchFlights(request));
    }

    /**
     * Test normal scenario for booking a flight
     */
    @Test
    void testBookFlight_Normal() {
        BookingRequest bookingRequest = new BookingRequest();
        BookingConfirmation confirmation = new BookingConfirmation();
        doNothing().when(validationUtil).validateBookingRequest(bookingRequest);
        when(bookingService.createBooking(bookingRequest)).thenReturn(confirmation);

        ResponseEntity<?> response = flightController.bookFlight(bookingRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(confirmation, response.getBody());
    }

    /**
     * Test error scenario: booking throws exception
     */
    @Test
    void testBookFlight_Exception() {
        BookingRequest bookingRequest = new BookingRequest();
        doNothing().when(validationUtil).validateBookingRequest(bookingRequest);
        when(bookingService.createBooking(bookingRequest)).thenThrow(new RuntimeException("Booking failed"));

        ResponseEntity<?> response = flightController.bookFlight(bookingRequest);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Booking failed", ((ErrorResponse)response.getBody()).getMessage());
    }

    /**
     * Test error scenario: invalid booking request
     */
    @Test
    void testBookFlight_InvalidRequest() {
        BookingRequest bookingRequest = new BookingRequest();
        doThrow(new IllegalArgumentException("Invalid booking")).when(validationUtil).validateBookingRequest(bookingRequest);
        ResponseEntity<?> response = flightController.bookFlight(bookingRequest);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Invalid booking", ((ErrorResponse)response.getBody()).getMessage());
    }

    /**
     * Test normal scenario for processing payment
     */
    @Test
    void testProcessPayment_Normal() {
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentResponse paymentResponse = new PaymentResponse();
        doNothing().when(validationUtil).validatePaymentRequest(paymentRequest);
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);

        ResponseEntity<?> response = flightController.processPayment(paymentRequest);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(paymentResponse, response.getBody());
    }

    /**
     * Test error scenario: payment throws exception
     */
    @Test
    void testProcessPayment_Exception() {
        PaymentRequest paymentRequest = new PaymentRequest();
        doNothing().when(validationUtil).validatePaymentRequest(paymentRequest);
        when(paymentService.processPayment(paymentRequest)).thenThrow(new RuntimeException("Payment failed"));

        ResponseEntity<?> response = flightController.processPayment(paymentRequest);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Payment failed", ((ErrorResponse)response.getBody()).getMessage());
    }

    /**
     * Test error scenario: invalid payment request
     */
    @Test
    void testProcessPayment_InvalidRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        doThrow(new IllegalArgumentException("Invalid payment")).when(validationUtil).validatePaymentRequest(paymentRequest);
        ResponseEntity<?> response = flightController.processPayment(paymentRequest);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Invalid payment", ((ErrorResponse)response.getBody()).getMessage());
    }

    /**
     * Test normal scenario for getting booking confirmation
     */
    @Test
    void testGetBookingConfirmation_Normal() {
        String bookingId = "ABC123";
        BookingConfirmation confirmation = new BookingConfirmation();
        when(bookingService.getBookingConfirmation(bookingId)).thenReturn(confirmation);

        ResponseEntity<?> response = flightController.getBookingConfirmation(bookingId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(confirmation, response.getBody());
    }

    /**
     * Test error scenario: booking confirmation throws exception
     */
    @Test
    void testGetBookingConfirmation_Exception() {
        String bookingId = "XYZ999";
        when(bookingService.getBookingConfirmation(bookingId)).thenThrow(new RuntimeException("Not found"));

        ResponseEntity<?> response = flightController.getBookingConfirmation(bookingId);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Not found", ((ErrorResponse)response.getBody()).getMessage());
    }
}
