package com.example.airtransport.controller;

import com.example.airtransport.model.*;
import com.example.airtransport.service.*;
import com.example.airtransport.util.*;
import com.example.airtransport.exception.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for all endpoints.
 */
class BookingControllerTest {
    @InjectMocks
    private BookingController bookingController;

    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- searchFlights() tests ---

    @Test
    @WithMockUser(roles = "USER")
    void searchFlights_validInput_returnsFlights() throws ExternalAPIException {
        // Purpose: Test normal scenario where valid input returns flight list
        List<Flight> flights = Arrays.asList(new Flight(), new Flight());
        when(flightSearchService.searchFlights("JFK", "2025-12-01", 2)).thenReturn(flights);
        when(AirportCodeUtil.isValid("JFK")).thenReturn(true);
        when(DateUtil.isValidFutureDate("2025-12-01")).thenReturn(true);
        ResponseEntity<?> response = bookingController.searchFlights("JFK", "2025-12-01", 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flights, response.getBody());
    }

    @Test
    @WithMockUser(roles = "USER")
    void searchFlights_invalidDestination_returnsBadRequest() {
        // Purpose: Test invalid destination input
        when(AirportCodeUtil.isValid("")).thenReturn(false);
        ResponseEntity<?> response = bookingController.searchFlights("", "2025-12-01", 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Invalid destination", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void searchFlights_invalidDate_returnsBadRequest() {
        // Purpose: Test invalid date input
        when(AirportCodeUtil.isValid("JFK")).thenReturn(true);
        when(DateUtil.isValidFutureDate("2020-01-01")).thenReturn(false);
        ResponseEntity<?> response = bookingController.searchFlights("JFK", "2020-01-01", 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("Invalid date", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void searchFlights_externalApiException_returnsServiceUnavailable() throws ExternalAPIException {
        // Purpose: Test error handling when external API fails
        when(AirportCodeUtil.isValid("JFK")).thenReturn(true);
        when(DateUtil.isValidFutureDate("2025-12-01")).thenReturn(true);
        when(flightSearchService.searchFlights(anyString(), anyString(), anyInt())).thenThrow(new ExternalAPIException("API down"));
        ResponseEntity<?> response = bookingController.searchFlights("JFK", "2025-12-01", 1);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        assertEquals("API down", ((ErrorResponse) response.getBody()).getMessage());
    }

    // --- bookFlight() tests ---

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_validRequest_returnsConfirmation() throws Exception {
        // Purpose: Test normal booking flow
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        PaymentInfo paymentInfo = new PaymentInfo();
        req.setPaymentInfo(paymentInfo);
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(true);
        PaymentResult paymentResult = new PaymentResult(true, "OK");
        when(paymentService.processPayment(paymentInfo)).thenReturn(paymentResult);
        Booking booking = new Booking();
        booking.setBookingId("B456");
        booking.setStatus("CONFIRMED");
        when(bookingRepository.saveBooking(req, paymentResult)).thenReturn(booking);
        doNothing().when(notificationService).sendConfirmation(booking);
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof BookingResponse);
        assertEquals("B456", ((BookingResponse) response.getBody()).getBookingId());
        assertEquals("CONFIRMED", ((BookingResponse) response.getBody()).getStatus());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_missingFlightId_returnsBadRequest() {
        // Purpose: Test missing flightId
        BookingRequest req = new BookingRequest();
        req.setFlightId("");
        req.setUserId("U001");
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Flight ID is required", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_missingUserId_returnsBadRequest() {
        // Purpose: Test missing userId
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("");
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User ID is required", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_seatUnavailable_returnsConflict() throws Exception {
        // Purpose: Test seat unavailable scenario
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(false);
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Seat not available", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_paymentFails_returnsPaymentRequired() throws Exception {
        // Purpose: Test payment failure scenario
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        PaymentInfo paymentInfo = new PaymentInfo();
        req.setPaymentInfo(paymentInfo);
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(true);
        PaymentResult paymentResult = new PaymentResult(false, "Declined");
        when(paymentService.processPayment(paymentInfo)).thenReturn(paymentResult);
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());
        assertEquals("Payment failed", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_paymentProcessingException_returnsPaymentRequired() throws Exception {
        // Purpose: Test payment processing exception
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        PaymentInfo paymentInfo = new PaymentInfo();
        req.setPaymentInfo(paymentInfo);
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(true);
        when(paymentService.processPayment(paymentInfo)).thenThrow(new PaymentProcessingException("Card error"));
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.PAYMENT_REQUIRED, response.getStatusCode());
        assertEquals("Card error", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_seatUnavailableException_returnsConflict() throws Exception {
        // Purpose: Test seat unavailable exception
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        PaymentInfo paymentInfo = new PaymentInfo();
        req.setPaymentInfo(paymentInfo);
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(true);
        when(paymentService.processPayment(paymentInfo)).thenThrow(new SeatUnavailableException("No seats left"));
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("No seats left", ((ErrorResponse) response.getBody()).getMessage());
    }

    @Test
    @WithMockUser(roles = "USER")
    void bookFlight_unexpectedException_returnsInternalServerError() throws Exception {
        // Purpose: Test unexpected exception
        BookingRequest req = new BookingRequest();
        req.setFlightId("F123");
        req.setUserId("U001");
        PaymentInfo paymentInfo = new PaymentInfo();
        req.setPaymentInfo(paymentInfo);
        when(flightSearchService.isSeatAvailable("F123")).thenReturn(true);
        when(paymentService.processPayment(paymentInfo)).thenThrow(new RuntimeException("Unknown error"));
        ResponseEntity<?> response = bookingController.bookFlight(req);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(((ErrorResponse) response.getBody()).getMessage().contains("Booking failed: Unknown error"));
    }

    // --- confirmBooking() tests ---

    @Test
    @WithMockUser(roles = "USER")
    void confirmBooking_validId_returnsBookingResponse() {
        // Purpose: Test normal confirmation
        Booking booking = new Booking();
        booking.setBookingId("B456");
        booking.setStatus("CONFIRMED");
        when(bookingRepository.findByBookingId("B456")).thenReturn(booking);
        ResponseEntity<?> response = bookingController.confirmBooking("B456");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof BookingResponse);
        assertEquals("B456", ((BookingResponse) response.getBody()).getBookingId());
        assertEquals("CONFIRMED", ((BookingResponse) response.getBody()).getStatus());
    }

    @Test
    @WithMockUser(roles = "USER")
    void confirmBooking_notFound_returnsNotFound() {
        // Purpose: Test booking not found
        when(bookingRepository.findByBookingId("B999")).thenReturn(null);
        ResponseEntity<?> response = bookingController.confirmBooking("B999");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Booking not found", ((ErrorResponse) response.getBody()).getMessage());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
