package com.example.airlinebooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Seat entity.
 */
class SeatTest {
    private Seat seat;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = Flight.builder().flightId("F001").build();
        seat = Seat.builder()
                .seatId("S001")
                .flight(flight)
                .seatNumber("A1")
                .isAvailable(true)
                .build();
    }

    /**
     * Test normal creation of Seat entity.
     */
    @Test
    @DisplayName("Seat entity is created with all fields set")
    void testSeatCreation_Normal() {
        assertEquals("S001", seat.getSeatId());
        assertEquals(flight, seat.getFlight());
        assertEquals("A1", seat.getSeatNumber());
        assertTrue(seat.getIsAvailable());
    }

    /**
     * Test edge case: null seatNumber.
     */
    @Test
    @DisplayName("Seat with null seatNumber should allow setting and getting")
    void testSeat_NullSeatNumber() {
        seat.setSeatNumber(null);
        assertNull(seat.getSeatNumber());
    }

    /**
     * Test boundary case: empty seatId.
     */
    @Test
    @DisplayName("Seat with empty seatId")
    void testSeat_EmptySeatId() {
        seat.setSeatId("");
        assertEquals("", seat.getSeatId());
    }

    /**
     * Test error scenario: null flight.
     */
    @Test
    @DisplayName("Seat with null flight should allow setting and getting")
    void testSeat_NullFlight() {
        seat.setFlight(null);
        assertNull(seat.getFlight());
    }

    /**
     * Test edge case: isAvailable false.
     */
    @Test
    @DisplayName("Seat isAvailable can be set to false")
    void testSeat_IsAvailableFalse() {
        seat.setIsAvailable(false);
        assertFalse(seat.getIsAvailable());
    }
}
