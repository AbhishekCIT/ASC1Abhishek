package com.airtransport.controller;

import com.airtransport.model.*;
import com.airtransport.service.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightController.
 * Covers normal, edge, boundary, and error scenarios for all endpoints.
 */
class FlightControllerTest {
    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @Mock
    private PaymentGatewayService paymentGatewayService;
    @Mock
    private EmailNotificationService emailNotificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal flight search scenario.
     */
    @Test
    void testSearchFlights_Normal() {
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.searchFlights("JFK", "LAX", "2025-11-01")).thenReturn(flights);
        ResponseEntity<List<Flight>> response = flightController.searchFlights("JFK", "LAX", "2025-11-01");
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Test flight search with invalid airport code (edge case).
     */
    @Test
    void testSearchFlights_InvalidAirportCode() {
        when(flightSearchService.searchFlights("XXX", "LAX", "2025-11-01")).thenReturn(Collections.emptyList());
        ResponseEntity<List<Flight>> response = flightController.searchFlights("XXX", "LAX", "2025-11-01");
        assertTrue(response.getBody().isEmpty());
        assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Test booking a flight successfully.
     */
    @Test
    void testBookFlight_Success() {
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123"); req.setUserId("U456"); req.setPassengerDetails(new User()); req.setPaymentInfo(new PaymentInfo());
        BookingResponse bookingResponse = new BookingResponse();
        when(bookingService.bookFlight(any(), any(), any(), any())).thenReturn(bookingResponse);
        ResponseEntity<?> response = flightController.bookFlight(req);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookingResponse, response.getBody());
    }

    /**
     * Test booking a flight with payment failure (error scenario).
     */
    @Test
    void testBookFlight_PaymentFailure() {
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123"); req.setUserId("U456"); req.setPassengerDetails(new User()); req.setPaymentInfo(new PaymentInfo());
        when(bookingService.bookFlight(any(), any(), any(), any())).thenThrow(new RuntimeException("Payment failed"));
        ResponseEntity<?> response = flightController.bookFlight(req);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Payment failed"));
    }

    /**
     * Test process payment successfully.
     */
    @Test
    void testProcessPayment_Success() {
        PaymentRequest req = new PaymentRequest();
        req.setBookingId("B789"); req.setPaymentInfo(new PaymentInfo());
        PaymentResponse paymentResponse = new PaymentResponse();
        when(paymentGatewayService.processPayment(any(), any())).thenReturn(paymentResponse);
        ResponseEntity<?> response = flightController.processPayment(req);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(paymentResponse, response.getBody());
    }

    /**
     * Test process payment with invalid card (error scenario).
     */
    @Test
    void testProcessPayment_InvalidCard() {
        PaymentRequest req = new PaymentRequest();
        req.setBookingId("B789"); req.setPaymentInfo(new PaymentInfo());
        when(paymentGatewayService.processPayment(any(), any())).thenThrow(new RuntimeException("Invalid card"));
        ResponseEntity<?> response = flightController.processPayment(req);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Invalid card"));
    }

    /**
     * Test sending booking confirmation email successfully.
     */
    @Test
    void testSendBookingConfirmation_Success() {
        EmailRequest req = new EmailRequest();
        req.setBookingId("B789"); req.setEmail("user@domain.com");
        EmailResponse emailResponse = new EmailResponse();
        when(emailNotificationService.sendBookingConfirmation(any(), any())).thenReturn(emailResponse);
        ResponseEntity<?> response = flightController.sendBookingConfirmation(req);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(emailResponse, response.getBody());
    }

    /**
     * Test sending booking confirmation email failure (error scenario).
     */
    @Test
    void testSendBookingConfirmation_Failure() {
        EmailRequest req = new EmailRequest();
        req.setBookingId("B789"); req.setEmail("user@domain.com");
        when(emailNotificationService.sendBookingConfirmation(any(), any())).thenThrow(new RuntimeException("Email could not be sent"));
        ResponseEntity<?> response = flightController.sendBookingConfirmation(req);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Email could not be sent"));
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
