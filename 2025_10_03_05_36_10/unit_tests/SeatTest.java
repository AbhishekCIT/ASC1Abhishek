package com.airtransport.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Seat model.
 * Covers all getters and setters, including edge and boundary cases.
 */
public class SeatTest {
    private Seat seat;

    @BeforeEach
    void setUp() {
        seat = new Seat();
    }

    /**
     * Test setting and getting seatNumber.
     */
    @Test
    void testSeatNumber() {
        seat.setSeatNumber("1A");
        assertEquals("1A", seat.getSeatNumber());
        seat.setSeatNumber(null);
        assertNull(seat.getSeatNumber());
    }

    /**
     * Test setting and getting seatClass.
     */
    @Test
    void testSeatClass() {
        seat.setSeatClass("Economy");
        assertEquals("Economy", seat.getSeatClass());
        seat.setSeatClass(null);
        assertNull(seat.getSeatClass());
    }

    /**
     * Test setting and getting isAvailable.
     * Purpose: Normal, true, false, and null cases.
     */
    @Test
    void testIsAvailable() {
        seat.setIsAvailable(true);
        assertTrue(seat.getIsAvailable());
        seat.setIsAvailable(false);
        assertFalse(seat.getIsAvailable());
        seat.setIsAvailable(null);
        assertNull(seat.getIsAvailable());
    }

    /**
     * Test setting and getting flight.
     */
    @Test
    void testFlight() {
        Flight flight = new Flight();
        flight.setFlightId(1);
        seat.setFlight(flight);
        assertEquals(flight, seat.getFlight());
        seat.setFlight(null);
        assertNull(seat.getFlight());
    }
}
