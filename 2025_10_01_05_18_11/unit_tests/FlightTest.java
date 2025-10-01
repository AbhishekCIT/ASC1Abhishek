package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight model.
 */
class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test normal scenario: setting and getting all fields.
     */
    @Test
    @DisplayName("Set and get all fields")
    void testSetAndGetFields() {
        String flightNumber = "DL123";
        String airline = "Delta";
        LocalDateTime departure = LocalDateTime.of(2025, 12, 1, 8, 0);
        LocalDateTime arrival = LocalDateTime.of(2025, 12, 1, 11, 0);
        double price = 450.0;
        int stops = 1;
        int availableSeats = 5;

        flight.setFlightNumber(flightNumber);
        flight.setAirline(airline);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPrice(price);
        flight.setStops(stops);
        flight.setAvailableSeats(availableSeats);

        assertEquals(flightNumber, flight.getFlightNumber());
        assertEquals(airline, flight.getAirline());
        assertEquals(departure, flight.getDeparture());
        assertEquals(arrival, flight.getArrival());
        assertEquals(price, flight.getPrice());
        assertEquals(stops, flight.getStops());
        assertEquals(availableSeats, flight.getAvailableSeats());
    }

    /**
     * Test normal scenario: parameterized constructor.
     */
    @Test
    @DisplayName("Parameterized constructor sets all fields")
    void testParameterizedConstructor() {
        LocalDateTime dep = LocalDateTime.of(2025, 12, 1, 8, 0);
        LocalDateTime arr = LocalDateTime.of(2025, 12, 1, 11, 0);
        Flight f = new Flight("UA456", "United", dep, arr, 470.0, 0, 3);
        assertEquals("UA456", f.getFlightNumber());
        assertEquals("United", f.getAirline());
        assertEquals(dep, f.getDeparture());
        assertEquals(arr, f.getArrival());
        assertEquals(470.0, f.getPrice());
        assertEquals(0, f.getStops());
        assertEquals(3, f.getAvailableSeats());
    }

    /**
     * Test getDurationMinutes for normal scenario.
     */
    @Test
    @DisplayName("getDurationMinutes returns correct duration")
    void testGetDurationMinutes_Normal() {
        LocalDateTime dep = LocalDateTime.of(2025, 12, 1, 8, 0);
        LocalDateTime arr = LocalDateTime.of(2025, 12, 1, 11, 0);
        flight.setDeparture(dep);
        flight.setArrival(arr);
        assertEquals(180, flight.getDurationMinutes());
    }

    /**
     * Test edge case: getDurationMinutes with null departure or arrival.
     */
    @Test
    @DisplayName("getDurationMinutes throws NullPointerException for null fields")
    void testGetDurationMinutes_NullFields() {
        flight.setDeparture(null);
        flight.setArrival(null);
        assertThrows(NullPointerException.class, () -> flight.getDurationMinutes());
    }

    /**
     * Test boundary condition: zero duration.
     */
    @Test
    @DisplayName("getDurationMinutes returns zero for same departure and arrival")
    void testGetDurationMinutes_ZeroDuration() {
        LocalDateTime dt = LocalDateTime.of(2025, 12, 1, 8, 0);
        flight.setDeparture(dt);
        flight.setArrival(dt);
        assertEquals(0, flight.getDurationMinutes());
    }
}
