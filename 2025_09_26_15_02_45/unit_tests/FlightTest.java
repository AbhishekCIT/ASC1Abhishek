package com.example.airbooking.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    @Test
    @DisplayName("Test all getters and setters for Flight")
    void testGettersAndSetters() {
        Flight flight = new Flight();
        flight.setFlightId(1L);
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        LocalDateTime dt = LocalDateTime.of(2025, 10, 1, 9, 0);
        flight.setDepartureTime(dt);
        flight.setPrice(350.00);

        assertEquals(1L, flight.getFlightId());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dt, flight.getDepartureTime());
        assertEquals(350.00, flight.getPrice());
    }

    @Test
    @DisplayName("Test Flight all-args constructor")
    void testAllArgsConstructor() {
        LocalDateTime dt = LocalDateTime.of(2025, 10, 1, 9, 0);
        Flight flight = new Flight(2L, "SFO", "ORD", dt, 200.00);
        assertEquals(2L, flight.getFlightId());
        assertEquals("SFO", flight.getOrigin());
        assertEquals("ORD", flight.getDestination());
        assertEquals(dt, flight.getDepartureTime());
        assertEquals(200.00, flight.getPrice());
    }

    @Test
    @DisplayName("Test Flight with null and edge values")
    void testNullAndEdgeValues() {
        Flight flight = new Flight();
        flight.setFlightId(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDepartureTime(null);
        flight.setPrice(null);
        assertNull(flight.getFlightId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getPrice());
    }
}
