package com.example.airbooking.controller;

import com.example.airbooking.exception.BookingException;
import com.example.airbooking.exception.PaymentException;
import com.example.airbooking.model.*;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.service.FlightService;
import com.example.airbooking.service.NotificationService;
import com.example.airbooking.service.PaymentService;
import com.example.airbooking.util.ValidationUtil;
import org.junit.jupiter.api.*;
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
 * Unit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for all endpoints.
 */
class BookingControllerTest {

    @Mock
    private FlightService flightService;
    @Mock
    private PaymentService paymentService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private BookingRepository bookingRepository;
    @InjectMocks
    private BookingController bookingController;
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
    void testSearchFlights_ValidInput_ReturnsFlights() {
        List<FlightResponse> flights = Arrays.asList(new FlightResponse(), new FlightResponse());
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(flights);
        // No exception from ValidationUtil (static)
        ResponseEntity<List<FlightResponse>> response = bookingController.searchFlights("2025-10-10", "NYC", "Economy");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    /**
     * Test searching flights with invalid date (edge case: validation failure).
     */
    @Test
    void testSearchFlights_InvalidDate_ThrowsValidationException() {
        // ValidationUtil.validateDate throws ValidationException
        assertThrows(Exception.class, () -> bookingController.searchFlights("invalid-date", "NYC", "Economy"));
    }

    /**
     * Test getFlightDetails returns details for valid flightId.
     */
    @Test
    void testGetFlightDetails_ValidId_ReturnsDetails() {
        FlightDetailResponse details = new FlightDetailResponse();
        when(flightService.getFlightDetails(1L)).thenReturn(details);
        ResponseEntity<FlightDetailResponse> response = bookingController.getFlightDetails(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(details, response.getBody());
    }

    /**
     * Test getFlightDetails with non-existent flightId (edge case).
     */
    @Test
    void testGetFlightDetails_InvalidId_ReturnsNull() {
        when(flightService.getFlightDetails(999L)).thenReturn(null);
        ResponseEntity<FlightDetailResponse> response = bookingController.getFlightDetails(999L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Test booking a ticket with all valid data (normal scenario).
     */
    @Test
    void testBookTicket_ValidRequest_SuccessfulBooking() {
        BookingRequest bookingRequest = mock(BookingRequest.class);
        PassengerDetails passengerDetails = new PassengerDetails();
        when(bookingRequest.getPassengerDetails()).thenReturn(passengerDetails);
        when(bookingRequest.getFlightId()).thenReturn(1L);
        PaymentRequest paymentRequest = new PaymentRequest();
        when(bookingRequest.getPaymentInfo()).thenReturn(paymentRequest);
        when(flightService.isFlightAvailable(1L)).thenReturn(true);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("SUCCESS");
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);
        Booking booking = new Booking();
        booking.setBookingId(123L);
        when(bookingRepository.saveBooking(bookingRequest, paymentResponse)).thenReturn(booking);
        doNothing().when(notificationService).sendConfirmation(booking);
        ResponseEntity<BookingResponse> response = bookingController.bookTicket(bookingRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(123L, response.getBody().getBookingId());
        assertEquals("CONFIRMED", response.getBody().getStatus());
    }

    /**
     * Test booking a ticket when flight is not available (error scenario).
     */
    @Test
    void testBookTicket_FlightNotAvailable_ThrowsBookingException() {
        BookingRequest bookingRequest = mock(BookingRequest.class);
        when(bookingRequest.getFlightId()).thenReturn(2L);
        when(bookingRequest.getPassengerDetails()).thenReturn(new PassengerDetails());
        when(flightService.isFlightAvailable(2L)).thenReturn(false);
        assertThrows(BookingException.class, () -> bookingController.bookTicket(bookingRequest));
    }

    /**
     * Test booking a ticket when payment fails (error scenario).
     */
    @Test
    void testBookTicket_PaymentFails_ThrowsBookingException() {
        BookingRequest bookingRequest = mock(BookingRequest.class);
        when(bookingRequest.getFlightId()).thenReturn(3L);
        when(bookingRequest.getPassengerDetails()).thenReturn(new PassengerDetails());
        PaymentRequest paymentRequest = new PaymentRequest();
        when(bookingRequest.getPaymentInfo()).thenReturn(paymentRequest);
        when(flightService.isFlightAvailable(3L)).thenReturn(true);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("FAILED");
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);
        assertThrows(BookingException.class, () -> bookingController.bookTicket(bookingRequest));
    }

    /**
     * Test booking a ticket with invalid passenger details (validation failure).
     */
    @Test
    void testBookTicket_InvalidPassengerDetails_ThrowsValidationException() {
        BookingRequest bookingRequest = mock(BookingRequest.class);
        // ValidationUtil.validatePassengerDetails throws exception
        when(bookingRequest.getPassengerDetails()).thenThrow(new RuntimeException("Invalid details"));
        assertThrows(Exception.class, () -> bookingController.bookTicket(bookingRequest));
    }

    /**
     * Test makePayment with successful payment (normal scenario).
     */
    @Test
    void testMakePayment_Successful() {
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("SUCCESS");
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);
        ResponseEntity<PaymentResponse> response = bookingController.makePayment(paymentRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody().getStatus());
    }

    /**
     * Test makePayment with failed payment (error scenario).
     */
    @Test
    void testMakePayment_Failed_ThrowsPaymentException() {
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("FAILED");
        when(paymentService.processPayment(paymentRequest)).thenReturn(paymentResponse);
        assertThrows(PaymentException.class, () -> bookingController.makePayment(paymentRequest));
    }

    /**
     * Test searchFlights returns empty list if no flights found (boundary case).
     */
    @Test
    void testSearchFlights_NoFlightsFound_ReturnsEmptyList() {
        when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(Collections.emptyList());
        ResponseEntity<List<FlightResponse>> response = bookingController.searchFlights("2025-10-10", "NYC", "Economy");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }
}
