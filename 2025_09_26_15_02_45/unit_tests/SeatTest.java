package com.example.airbooking.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {
    @Test
    @DisplayName("Test all getters and setters for Seat")
    void testGettersAndSetters() {
        Seat seat = new Seat();
        seat.setSeatId(1L);
        seat.setFlightId(2L);
        seat.setSeatNo("12A");
        seat.setAvailable(true);
        assertEquals(1L, seat.getSeatId());
        assertEquals(2L, seat.getFlightId());
        assertEquals("12A", seat.getSeatNo());
        assertTrue(seat.getAvailable());
    }

    @Test
    @DisplayName("Test Seat all-args constructor")
    void testAllArgsConstructor() {
        Seat seat = new Seat(3L, 4L, "14B", false);
        assertEquals(3L, seat.getSeatId());
        assertEquals(4L, seat.getFlightId());
        assertEquals("14B", seat.getSeatNo());
        assertFalse(seat.getAvailable());
    }

    @Test
    @DisplayName("Test Seat with null and edge values")
    void testNullAndEdgeValues() {
        Seat seat = new Seat();
        seat.setSeatId(null);
        seat.setFlightId(null);
        seat.setSeatNo(null);
        seat.setAvailable(null);
        assertNull(seat.getSeatId());
        assertNull(seat.getFlightId());
        assertNull(seat.getSeatNo());
        assertNull(seat.getAvailable());
    }
}
