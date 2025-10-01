package com.example.airlinebooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        flight = Flight.builder()
                .flightId("F001")
                .airline("AI")
                .origin("DEL")
                .destination("BLR")
                .date(LocalDate.of(2025, 10, 15))
                .fare(5000.0)
                .build();
    }

    /**
     * Test normal creation of Flight entity.
     */
    @Test
    @DisplayName("Flight entity is created with all fields set")
    void testFlightCreation_Normal() {
        assertEquals("F001", flight.getFlightId());
        assertEquals("AI", flight.getAirline());
        assertEquals("DEL", flight.getOrigin());
        assertEquals("BLR", flight.getDestination());
        assertEquals(LocalDate.of(2025, 10, 15), flight.getDate());
        assertEquals(5000.0, flight.getFare());
    }

    /**
     * Test edge case: null airline.
     */
    @Test
    @DisplayName("Flight with null airline should allow setting and getting")
    void testFlight_NullAirline() {
        flight.setAirline(null);
        assertNull(flight.getAirline());
    }

    /**
     * Test boundary case: empty flightId.
     */
    @Test
    @DisplayName("Flight with empty flightId")
    void testFlight_EmptyFlightId() {
        flight.setFlightId("");
        assertEquals("", flight.getFlightId());
    }

    /**
     * Test error scenario: negative fare.
     */
    @Test
    @DisplayName("Flight with negative fare")
    void testFlight_NegativeFare() {
        flight.setFare(-100.0);
        assertEquals(-100.0, flight.getFare());
    }

    /**
     * Test edge case: null date.
     */
    @Test
    @DisplayName("Flight with null date should allow setting and getting")
    void testFlight_NullDate() {
        flight.setDate(null);
        assertNull(flight.getDate());
    }
}
