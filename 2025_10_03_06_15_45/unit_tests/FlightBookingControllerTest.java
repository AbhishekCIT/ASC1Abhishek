package com.airline.flightbooking.controller;

import com.airline.flightbooking.dto.*;
import com.airline.flightbooking.model.*;
import com.airline.flightbooking.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightBookingController.
 * Covers all endpoints, normal and edge cases.
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

    /**
     * Test searchFlights endpoint for normal scenario.
     */
    @Test
    void testSearchFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().toString());
        List<Flight> flights = Collections.singletonList(Flight.builder().flightId("XY123").origin("JFK").destination("LAX").date(LocalDate.now()).price(350).time("10:00").build());
        when(flightSearchService.searchFlights(anyString(), anyString(), any(LocalDate.class))).thenReturn(flights);
        ResponseEntity<FlightSearchResponse> response = controller.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getFlights().size());
    }

    /**
     * Test bookTicket endpoint for normal scenario.
     */
    @Test
    void testBookTicket_Normal() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("XY123");
        Passenger passenger = Passenger.builder().passengerId("P1").name("John Doe").contact("1234567890").id("ID123").build();
        request.setPassenger(passenger);
        Booking booking = Booking.builder().bookingId("BK456").status("PENDING").flight(null).passenger(passenger).bookingDate(LocalDate.now()).build();
        when(bookingService.createBooking(anyString(), any(Passenger.class))).thenReturn(booking);
        ResponseEntity<BookingResponse> response = controller.bookTicket(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("BK456", response.getBody().getBookingId());
    }

    /**
     * Test processPayment endpoint for successful payment.
     */
    @Test
    void testProcessPayment_Success() {
        PaymentRequest request = new PaymentRequest();
        request.setBookingId("BK456");
        request.setAmount(350);
        request.setPaymentMethod("CARD");
        Payment payment = Payment.builder().paymentId("PM789").booking(null).amount(350).status("SUCCESS").build();
        when(paymentService.processPayment(anyString(), anyDouble(), anyString())).thenReturn(payment);
        doNothing().when(bookingService).confirmBooking(anyString());
        ResponseEntity<PaymentResponse> response = controller.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("SUCCESS", response.getBody().getStatus());
    }

    /**
     * Test processPayment endpoint for failed payment.
     */
    @Test
    void testProcessPayment_Failure() {
        PaymentRequest request = new PaymentRequest();
        request.setBookingId("BK456");
        request.setAmount(350);
        request.setPaymentMethod("CARD");
        Payment payment = Payment.builder().paymentId("PM789").booking(null).amount(350).status("FAILED").build();
        when(paymentService.processPayment(anyString(), anyDouble(), anyString())).thenReturn(payment);
        ResponseEntity<PaymentResponse> response = controller.processPayment(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("FAILED", response.getBody().getStatus());
        // confirmBooking should not be called
        verify(bookingService, never()).confirmBooking(anyString());
    }
}
