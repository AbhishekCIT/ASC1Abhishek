package com.airtransport.service;

import com.airtransport.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightService.
 */
class FlightServiceTest {

    private FlightService flightService;

    @BeforeEach
    void setUp() {
        flightService = new FlightService();
    }

    /**
     * Test successful flight search scenario.
     */
    @Test
    void testSearchFlights_Success() {
        String origin = "JFK";
        String destination = "LAX";
        String date = LocalDate.now().plusDays(1).toString();
        List<Flight> flights = flightService.searchFlights(origin, destination, date);
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
        assertEquals("F123", flights.get(0).getFlightId());
    }

    /**
     * Test search with invalid origin code (edge case).
     */
    @Test
    void testSearchFlights_InvalidOrigin() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                flightService.searchFlights("", "LAX", LocalDate.now().plusDays(1).toString()));
        assertEquals("Invalid origin airport code", ex.getMessage());
    }

    /**
     * Test search with invalid destination code (edge case).
     */
    @Test
    void testSearchFlights_InvalidDestination() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                flightService.searchFlights("JFK", "", LocalDate.now().plusDays(1).toString()));
        assertEquals("Invalid destination airport code", ex.getMessage());
    }

    /**
     * Test search with invalid date format (boundary condition).
     */
    @Test
    void testSearchFlights_InvalidDateFormat() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                flightService.searchFlights("JFK", "LAX", "not-a-date"));
        assertEquals("Invalid travel date", ex.getMessage());
    }

    /**
     * Test search with past date (boundary condition).
     */
    @Test
    void testSearchFlights_PastDate() {
        String pastDate = LocalDate.now().minusDays(1).toString();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                flightService.searchFlights("JFK", "LAX", pastDate));
        assertEquals("Invalid travel date", ex.getMessage());
    }
}
