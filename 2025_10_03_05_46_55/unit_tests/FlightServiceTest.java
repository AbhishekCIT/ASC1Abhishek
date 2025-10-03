package com.asc.airbooking.service;

import com.asc.airbooking.entity.Flight;
import com.asc.airbooking.entity.Booking;
import com.asc.airbooking.model.BookFlightRequest;
import com.asc.airbooking.model.BookFlightResponse;
import com.asc.airbooking.model.FlightSearchRequest;
import com.asc.airbooking.model.FlightSearchResponse;
import com.asc.airbooking.repository.FlightRepository;
import com.asc.airbooking.repository.BookingRepository;
import com.asc.airbooking.util.AirportCodeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightService.
 * Covers normal, edge, boundary, and error scenarios for findFlights and bookFlight.
 */
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PaymentService paymentService;
    @Mock
    private AirportCodeUtil airportCodeUtil;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Purpose: Test successful flight search.
     */
    @Test
    void testFindFlights_Success() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        Flight flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.now().plusDays(1), 320.00);
        when(airportCodeUtil.isValidAirportCode("JFK")).thenReturn(true);
        when(airportCodeUtil.isValidAirportCode("LAX")).thenReturn(true);
        when(flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(anyString(), anyString(), any(), any())).thenReturn(Collections.singletonList(flight));
        FlightSearchResponse response = flightService.findFlights(request);
        assertNotNull(response);
        assertEquals(1, response.getFlights().size());
        assertEquals("FL123", response.getFlights().get(0).getFlightId());
    }

    /**
     * Purpose: Test flight search with invalid origin code (should throw IllegalArgumentException).
     */
    @Test
    void testFindFlights_InvalidOrigin() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("XXX");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        when(airportCodeUtil.isValidAirportCode("XXX")).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> flightService.findFlights(request));
    }

    /**
     * Purpose: Test flight search with invalid destination code (should throw IllegalArgumentException).
     */
    @Test
    void testFindFlights_InvalidDestination() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("XXX");
        request.setDate(LocalDate.now().plusDays(1));
        when(airportCodeUtil.isValidAirportCode("JFK")).thenReturn(true);
        when(airportCodeUtil.isValidAirportCode("XXX")).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> flightService.findFlights(request));
    }

    /**
     * Purpose: Test flight search with past date (should throw IllegalArgumentException).
     */
    @Test
    void testFindFlights_PastDate() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().minusDays(1));
        when(airportCodeUtil.isValidAirportCode("JFK")).thenReturn(true);
        when(airportCodeUtil.isValidAirportCode("LAX")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> flightService.findFlights(request));
    }

    /**
     * Purpose: Test flight search with no flights found (should throw RuntimeException).
     */
    @Test
    void testFindFlights_NoFlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        when(airportCodeUtil.isValidAirportCode("JFK")).thenReturn(true);
        when(airportCodeUtil.isValidAirportCode("LAX")).thenReturn(true);
        when(flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(anyString(), anyString(), any(), any())).thenReturn(Collections.emptyList());
        assertThrows(RuntimeException.class, () -> flightService.findFlights(request));
    }

    /**
     * Purpose: Test successful booking of a flight.
     */
    @Test
    void testBookFlight_Success() {
        BookFlightRequest request = new BookFlightRequest();
        request.setFlightId("FL123");
        BookFlightRequest.Passenger passenger = new BookFlightRequest.Passenger();
        passenger.setName("John Doe");
        passenger.setEmail("john@example.com");
        request.setPassenger(passenger);
        BookFlightRequest.Payment payment = new BookFlightRequest.Payment();
        payment.setToken("tok_abc");
        request.setPayment(payment);
        Flight flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.now().plusDays(1), 320.00);
        when(flightRepository.findById("FL123")).thenReturn(Optional.of(flight));
        when(paymentService.processPayment(320.00, "tok_abc")).thenReturn(true);
        BookFlightResponse response = flightService.bookFlight(request);
        assertNotNull(response);
        assertEquals("confirmed", response.getStatus());
        assertNotNull(response.getConfirmationId());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    /**
     * Purpose: Test booking a flight with unavailable flight (should throw RuntimeException).
     */
    @Test
    void testBookFlight_FlightUnavailable() {
        BookFlightRequest request = new BookFlightRequest();
        request.setFlightId("FL999");
        when(flightRepository.findById("FL999")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> flightService.bookFlight(request));
    }

    /**
     * Purpose: Test booking a flight with failed payment (should throw RuntimeException).
     */
    @Test
    void testBookFlight_PaymentFailed() {
        BookFlightRequest request = new BookFlightRequest();
        request.setFlightId("FL123");
        BookFlightRequest.Passenger passenger = new BookFlightRequest.Passenger();
        passenger.setName("John Doe");
        passenger.setEmail("john@example.com");
        request.setPassenger(passenger);
        BookFlightRequest.Payment payment = new BookFlightRequest.Payment();
        payment.setToken("tok_abc");
        request.setPayment(payment);
        Flight flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.now().plusDays(1), 320.00);
        when(flightRepository.findById("FL123")).thenReturn(Optional.of(flight));
        when(paymentService.processPayment(320.00, "tok_abc")).thenReturn(false);
        assertThrows(RuntimeException.class, () -> flightService.bookFlight(request));
    }
}
