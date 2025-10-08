package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightController.
 * Covers normal, edge, boundary, and error scenarios for all endpoints.
 */
class FlightControllerTest {

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentGatewayService paymentGatewayService;
    @Mock
    private ConfirmationService confirmationService;

    @InjectMocks
    private FlightController flightController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test searching flights with valid input (normal scenario).
     */
    @Test
    void testSearchFlights_validRequest_returnsFlights() {
        FlightSearchRequest request = new FlightSearchRequest("JFK", "LAX", "2025-10-15");
        FlightSearchResponse response = new FlightSearchResponse(Collections.emptyList());
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        ResponseEntity<FlightSearchResponse> result = flightController.searchFlights(request);
        assertEquals(response, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * Test searching flights with empty origin (edge case).
     */
    @Test
    void testSearchFlights_emptyOrigin_returnsEmptyFlights() {
        FlightSearchRequest request = new FlightSearchRequest("", "LAX", "2025-10-15");
        FlightSearchResponse response = new FlightSearchResponse(Collections.emptyList());
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        ResponseEntity<FlightSearchResponse> result = flightController.searchFlights(request);
        assertEquals(response, result.getBody());
    }

    /**
     * Test booking a flight with valid input (normal scenario).
     */
    @Test
    void testBookFlight_validRequest_returnsBookingConfirmation() {
        BookingRequest request = mock(BookingRequest.class);
        BookingResponse response = mock(BookingResponse.class);
        when(bookingService.createBooking(request)).thenReturn(response);
        ResponseEntity<BookingResponse> result = flightController.bookFlight(request);
        assertEquals(response, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * Test booking a flight with null request (error scenario).
     */
    @Test
    void testBookFlight_nullRequest_throwsException() {
        when(bookingService.createBooking(null)).thenThrow(new IllegalArgumentException("Request cannot be null"));
        assertThrows(IllegalArgumentException.class, () -> flightController.bookFlight(null));
    }

    /**
     * Test making payment with valid input (normal scenario).
     */
    @Test
    void testMakePayment_validRequest_returnsPaymentStatus() {
        PaymentRequest request = mock(PaymentRequest.class);
        PaymentResponse response = mock(PaymentResponse.class);
        when(paymentGatewayService.processPayment(request)).thenReturn(response);
        ResponseEntity<PaymentResponse> result = flightController.makePayment(request);
        assertEquals(response, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * Test making payment with invalid payment info (error scenario).
     */
    @Test
    void testMakePayment_invalidPaymentInfo_throwsException() {
        PaymentRequest request = mock(PaymentRequest.class);
        when(paymentGatewayService.processPayment(request)).thenThrow(new RuntimeException("Invalid payment info"));
        assertThrows(RuntimeException.class, () -> flightController.makePayment(request));
    }

    /**
     * Test sending confirmation with valid input (normal scenario).
     */
    @Test
    void testSendConfirmation_validRequest_returnsConfirmationStatus() {
        ConfirmationRequest request = mock(ConfirmationRequest.class);
        ConfirmationResponse response = mock(ConfirmationResponse.class);
        when(confirmationService.sendConfirmation(request)).thenReturn(response);
        ResponseEntity<ConfirmationResponse> result = flightController.sendConfirmation(request);
        assertEquals(response, result.getBody());
        assertEquals(200, result.getStatusCodeValue());
    }

    /**
     * Test sending confirmation with null request (error scenario).
     */
    @Test
    void testSendConfirmation_nullRequest_throwsException() {
        when(confirmationService.sendConfirmation(null)).thenThrow(new IllegalArgumentException("Request cannot be null"));
        assertThrows(IllegalArgumentException.class, () -> flightController.sendConfirmation(null));
    }
}
