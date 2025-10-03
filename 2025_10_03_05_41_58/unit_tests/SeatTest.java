package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Seat entity.
 * Covers constructors, builder, getters, setters, equals, hashCode, and toString.
 */
class SeatTest {
    private Seat seat;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        seat = new Seat("S1", flight, true);
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        assertEquals("S1", seat.getSeatId());
        assertEquals(flight, seat.getFlight());
        assertTrue(seat.isBooked());
    }

    @Test
    @DisplayName("Test setters and no-args constructor")
    void testSettersAndNoArgsConstructor() {
        Seat s = new Seat();
        s.setSeatId("S2");
        s.setFlight(flight);
        s.setBooked(false);
        assertEquals("S2", s.getSeatId());
        assertEquals(flight, s.getFlight());
        assertFalse(s.isBooked());
    }

    @Test
    @DisplayName("Test builder pattern")
    void testBuilder() {
        Seat s = Seat.builder()
                .seatId("S3")
                .flight(flight)
                .isBooked(true)
                .build();
        assertEquals("S3", s.getSeatId());
        assertTrue(s.isBooked());
    }

    @Test
    @DisplayName("Test equals and hashCode")
    void testEqualsAndHashCode() {
        Seat s1 = new Seat("S1", flight, true);
        Seat s2 = new Seat("S1", flight, true);
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    @DisplayName("Test toString does not throw")
    void testToString() {
        assertDoesNotThrow(() -> seat.toString());
    }

    @Test
    @DisplayName("Test edge case: null fields")
    void testNullFields() {
        Seat s = new Seat(null, null, false);
        assertNull(s.getSeatId());
        assertNull(s.getFlight());
        assertFalse(s.isBooked());
    }
}
