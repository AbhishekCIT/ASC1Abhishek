package com.example.flightsearch.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Seat POJO (getters/setters and edge cases).
 */
class SeatTest {
    private Seat seat;

    @BeforeEach
    void setUp() {
        seat = new Seat();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    void testGettersAndSetters_NormalValues() {
        seat.setSeatId(1L);
        Flight flight = new Flight();
        flight.setFlightId(10L);
        seat.setFlight(flight);
        seat.setSeatNumber("12A");
        seat.setSeatClass("Economy");
        seat.setAvailable(true);

        assertEquals(1L, seat.getSeatId());
        assertEquals(flight, seat.getFlight());
        assertEquals("12A", seat.getSeatNumber());
        assertEquals("Economy", seat.getSeatClass());
        assertTrue(seat.isAvailable());
    }

    /**
     * Test edge case: null values for String fields and Flight.
     */
    @Test
    void testSetters_NullValues() {
        seat.setFlight(null);
        seat.setSeatNumber(null);
        seat.setSeatClass(null);
        assertNull(seat.getFlight());
        assertNull(seat.getSeatNumber());
        assertNull(seat.getSeatClass());
    }

    /**
     * Test boundary case: seatId zero and negative.
     */
    @Test
    void testSetters_SeatIdBoundary() {
        seat.setSeatId(0L);
        assertEquals(0L, seat.getSeatId());
        seat.setSeatId(-5L);
        assertEquals(-5L, seat.getSeatId());
    }

    /**
     * Test boolean setter and getter for isAvailable.
     */
    @Test
    void testSetters_IsAvailable() {
        seat.setAvailable(false);
        assertFalse(seat.isAvailable());
        seat.setAvailable(true);
        assertTrue(seat.isAvailable());
    }
}
