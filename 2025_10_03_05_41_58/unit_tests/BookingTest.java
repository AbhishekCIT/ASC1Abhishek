package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Booking entity.
 * Covers constructors, builder, getters, setters, equals, hashCode, and toString.
 */
class BookingTest {
    private Booking booking;
    private User user;
    private Flight flight;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        user = new User();
        flight = new Flight();
        now = LocalDateTime.now();
        booking = new Booking("BK123", user, flight, now, "CONFIRMED");
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        assertEquals("BK123", booking.getBookingId());
        assertEquals(user, booking.getUser());
        assertEquals(flight, booking.getFlight());
        assertEquals(now, booking.getBookingDate());
        assertEquals("CONFIRMED", booking.getStatus());
    }

    @Test
    @DisplayName("Test setters and no-args constructor")
    void testSettersAndNoArgsConstructor() {
        Booking b = new Booking();
        b.setBookingId("BK999");
        b.setUser(user);
        b.setFlight(flight);
        b.setBookingDate(now);
        b.setStatus("CANCELED");
        assertEquals("BK999", b.getBookingId());
        assertEquals(user, b.getUser());
        assertEquals(flight, b.getFlight());
        assertEquals(now, b.getBookingDate());
        assertEquals("CANCELED", b.getStatus());
    }

    @Test
    @DisplayName("Test builder pattern")
    void testBuilder() {
        Booking b = Booking.builder()
                .bookingId("BK777")
                .user(user)
                .flight(flight)
                .bookingDate(now)
                .status("PENDING")
                .build();
        assertEquals("BK777", b.getBookingId());
        assertEquals("PENDING", b.getStatus());
    }

    @Test
    @DisplayName("Test equals and hashCode")
    void testEqualsAndHashCode() {
        Booking b1 = new Booking("BK123", user, flight, now, "CONFIRMED");
        Booking b2 = new Booking("BK123", user, flight, now, "CONFIRMED");
        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    @DisplayName("Test toString does not throw")
    void testToString() {
        assertDoesNotThrow(() -> booking.toString());
    }

    @Test
    @DisplayName("Test edge case: null fields")
    void testNullFields() {
        Booking b = new Booking(null, null, null, null, null);
        assertNull(b.getBookingId());
        assertNull(b.getUser());
        assertNull(b.getFlight());
        assertNull(b.getBookingDate());
        assertNull(b.getStatus());
    }
}
