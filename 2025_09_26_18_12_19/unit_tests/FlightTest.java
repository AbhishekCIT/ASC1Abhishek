package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
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
     * Test default constructor and setters/getters.
     */
    @Test
    void testDefaultConstructorAndSetters() {
        LocalDate date = LocalDate.of(2024, 7, 10);
        flight.setFlightId("F123");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDate(date);
        flight.setPrice(350.0);
        flight.setFlightClass("Economy");
        flight.setAvailability(5);
        assertEquals("F123", flight.getFlightId());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(date, flight.getDate());
        assertEquals(350.0, flight.getPrice());
        assertEquals("Economy", flight.getFlightClass());
        assertEquals(5, flight.getAvailability());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        LocalDate date = LocalDate.of(2024, 7, 10);
        Flight f = new Flight("F456", "SFO", "ORD", date, 500.0, "Business", 2);
        assertEquals("F456", f.getFlightId());
        assertEquals("SFO", f.getOrigin());
        assertEquals("ORD", f.getDestination());
        assertEquals(date, f.getDate());
        assertEquals(500.0, f.getPrice());
        assertEquals("Business", f.getFlightClass());
        assertEquals(2, f.getAvailability());
    }

    /**
     * Test setting null and edge values.
     */
    @Test
    void testNullAndEdgeValues() {
        flight.setFlightId(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDate(null);
        flight.setPrice(0.0);
        flight.setFlightClass(null);
        flight.setAvailability(0);
        assertNull(flight.getFlightId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDate());
        assertEquals(0.0, flight.getPrice());
        assertNull(flight.getFlightClass());
        assertEquals(0, flight.getAvailability());
    }
}
