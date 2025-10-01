package com.example.flightsearch.entity;

import com.example.flightsearch.model.FlightDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test all getters and setters for Flight entity.
     */
    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        flight.setFlightId("F123");
        flight.setAirline("Delta");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(6);
        flight.setDepartureTime(dep);
        flight.setArrivalTime(arr);
        flight.setPrice(299.99);
        flight.setDuration("6h");

        assertEquals("F123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dep, flight.getDepartureTime());
        assertEquals(arr, flight.getArrivalTime());
        assertEquals(299.99, flight.getPrice());
        assertEquals("6h", flight.getDuration());
    }

    /**
     * Test getDurationMinutes with various formats.
     */
    @Test
    @DisplayName("getDurationMinutes parses hours and minutes correctly")
    void testGetDurationMinutes() {
        // Only hours
        flight.setDuration("3h");
        assertEquals(180, flight.getDurationMinutes());
        // Only minutes
        flight.setDuration("45m");
        assertEquals(45, flight.getDurationMinutes());
        // Hours and minutes
        flight.setDuration("2h 30m");
        assertEquals(150, flight.getDurationMinutes());
        // Edge: null duration
        flight.setDuration(null);
        assertEquals(0, flight.getDurationMinutes());
        // Edge: empty string
        flight.setDuration("");
        assertEquals(0, flight.getDurationMinutes());
    }

    /**
     * Test toFlightDTO conversion.
     */
    @Test
    @DisplayName("toFlightDTO returns correct DTO")
    void testToFlightDTO() {
        flight.setFlightId("F321");
        flight.setAirline("United");
        flight.setOrigin("ORD");
        flight.setDestination("SFO");
        LocalDateTime dep = LocalDateTime.of(2024, 10, 1, 8, 0);
        LocalDateTime arr = LocalDateTime.of(2024, 10, 1, 11, 0);
        flight.setDepartureTime(dep);
        flight.setArrivalTime(arr);
        flight.setPrice(400.0);
        flight.setDuration("3h");

        FlightDTO dto = flight.toFlightDTO();
        assertEquals("F321", dto.getFlightId());
        assertEquals("United", dto.getAirline());
        assertEquals("ORD", dto.getOrigin());
        assertEquals("SFO", dto.getDestination());
        assertEquals(dep.toString(), dto.getDepartureTime());
        assertEquals(arr.toString(), dto.getArrivalTime());
        assertEquals(400.0, dto.getPrice());
        assertEquals("3h", dto.getDuration());
    }

    /**
     * Test toFlightDTO with null times.
     */
    @Test
    @DisplayName("toFlightDTO handles null departure/arrival times")
    void testToFlightDTO_NullTimes() {
        flight.setDepartureTime(null);
        flight.setArrivalTime(null);
        FlightDTO dto = flight.toFlightDTO();
        assertNull(dto.getDepartureTime());
        assertNull(dto.getArrivalTime());
    }
}
