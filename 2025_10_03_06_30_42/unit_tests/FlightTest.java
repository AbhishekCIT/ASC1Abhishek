package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers normal, edge, boundary, and error scenarios for all methods.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test setting and getting flightId (normal scenario).
     */
    @Test
    void testFlightId() {
        flight.setFlightId("FL123");
        assertEquals("FL123", flight.getFlightId());
    }

    /**
     * Test setting and getting origin (normal scenario).
     */
    @Test
    void testOrigin() {
        flight.setOrigin("JFK");
        assertEquals("JFK", flight.getOrigin());
    }

    /**
     * Test setting and getting destination (normal scenario).
     */
    @Test
    void testDestination() {
        flight.setDestination("LAX");
        assertEquals("LAX", flight.getDestination());
    }

    /**
     * Test setting and getting date (normal scenario).
     */
    @Test
    void testDate() {
        LocalDate date = LocalDate.of(2025, 12, 1);
        flight.setDate(date);
        assertEquals(date, flight.getDate());
    }

    /**
     * Test setting and getting price (normal scenario).
     */
    @Test
    void testPrice() {
        BigDecimal price = new BigDecimal("350.00");
        flight.setPrice(price);
        assertEquals(price, flight.getPrice());
    }

    /**
     * Test setting and getting seatsAvailable (normal scenario).
     */
    @Test
    void testSeatsAvailable() {
        flight.setSeatsAvailable(10);
        assertEquals(10, flight.getSeatsAvailable());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testNullValues() {
        flight.setFlightId(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDate(null);
        flight.setPrice(null);
        assertNull(flight.getFlightId());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDate());
        assertNull(flight.getPrice());
    }

    /**
     * Test boundary condition for seatsAvailable (zero seats).
     */
    @Test
    void testSeatsAvailableZero() {
        flight.setSeatsAvailable(0);
        assertEquals(0, flight.getSeatsAvailable());
    }

    /**
     * Test boundary condition for negative seatsAvailable (error scenario).
     */
    @Test
    void testSeatsAvailableNegative() {
        flight.setSeatsAvailable(-1);
        assertEquals(-1, flight.getSeatsAvailable());
    }

    /**
     * Test boundary condition for price (zero price).
     */
    @Test
    void testPriceZero() {
        flight.setPrice(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, flight.getPrice());
    }
}
