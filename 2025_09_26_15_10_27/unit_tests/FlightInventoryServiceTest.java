package com.example.airtransport.service;

import com.example.airtransport.model.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightInventoryService.
 */
class FlightInventoryServiceTest {

    private final FlightInventoryService flightInventoryService = new FlightInventoryService();

    @Test
    @DisplayName("Test searchFlights returns correct flights for valid input")
    void testSearchFlights_ValidInput() {
        LocalDate date = LocalDate.of(2025, 10, 1);
        List<Flight> flights = flightInventoryService.searchFlights("JFK", "LAX", date);
        assertNotNull(flights);
        assertEquals(2, flights.size());
        assertEquals("JFK", flights.get(0).getOrigin());
        assertEquals("LAX", flights.get(0).getDestination());
        assertEquals(date.atTime(10, 0), flights.get(0).getDepartureTime());
    }

    @Test
    @DisplayName("Test searchFlights with different origin/destination/date")
    void testSearchFlights_DifferentInput() {
        LocalDate date = LocalDate.of(2025, 12, 25);
        List<Flight> flights = flightInventoryService.searchFlights("SFO", "ORD", date);
        assertNotNull(flights);
        assertEquals(2, flights.size());
        assertEquals("SFO", flights.get(1).getOrigin());
        assertEquals("ORD", flights.get(1).getDestination());
        assertEquals(date.atTime(15, 30), flights.get(1).getDepartureTime());
    }

    @Test
    @DisplayName("Test isSeatAvailable always returns true (demo stub)")
    void testIsSeatAvailable_AlwaysTrue() {
        assertTrue(flightInventoryService.isSeatAvailable("F123"));
        assertTrue(flightInventoryService.isSeatAvailable("F456"));
        assertTrue(flightInventoryService.isSeatAvailable(null));
        assertTrue(flightInventoryService.isSeatAvailable(""));
    }
}
