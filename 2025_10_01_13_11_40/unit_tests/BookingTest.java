package com.example.airlinebooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 */
class BookingTest {
    private Booking booking;
    private User user;
    private Flight flight;
    private Seat seat;

    @BeforeEach
    void setUp() {
        user = User.builder().userId("U001").name("John Doe").email("john@example.com").password("pass").build();
        flight = Flight.builder().flightId("F001").airline("AI").origin("DEL").destination("BLR").date("2025-10-15").fare(5000).build();
        seat = Seat.builder().seatId("S001").flight(flight).seatNumber("A1").isAvailable(true).build();
        booking = Booking.builder()
                .bookingId("B001")
                .user(user)
                .flight(flight)
                .seat(seat)
                .status("CONFIRMED")
                .createdAt(LocalDateTime.now())
                .build();
    }

    /**
     * Test normal creation of Booking entity.
     */
    @Test
    @DisplayName("Booking entity is created with all fields set")
    void testBookingCreation_Normal() {
        assertEquals("B001", booking.getBookingId());
        assertEquals(user, booking.getUser());
        assertEquals(flight, booking.getFlight());
        assertEquals(seat, booking.getSeat());
        assertEquals("CONFIRMED", booking.getStatus());
        assertNotNull(booking.getCreatedAt());
    }

    /**
     * Test edge case: null status.
     */
    @Test
    @DisplayName("Booking with null status should allow setting and getting")
    void testBooking_NullStatus() {
        booking.setStatus(null);
        assertNull(booking.getStatus());
    }

    /**
     * Test boundary case: empty bookingId.
     */
    @Test
    @DisplayName("Booking with empty bookingId")
    void testBooking_EmptyBookingId() {
        booking.setBookingId("");
        assertEquals("", booking.getBookingId());
    }

    /**
     * Test error scenario: null user.
     */
    @Test
    @DisplayName("Booking with null user should allow setting and getting")
    void testBooking_NullUser() {
        booking.setUser(null);
        assertNull(booking.getUser());
    }

    /**
     * Test error scenario: null flight.
     */
    @Test
    @DisplayName("Booking with null flight should allow setting and getting")
    void testBooking_NullFlight() {
        booking.setFlight(null);
        assertNull(booking.getFlight());
    }

    /**
     * Test error scenario: null seat.
     */
    @Test
    @DisplayName("Booking with null seat should allow setting and getting")
    void testBooking_NullSeat() {
        booking.setSeat(null);
        assertNull(booking.getSeat());
    }

    /**
     * Test boundary case: status values.
     */
    @Test
    @DisplayName("Booking status can be set to CANCELLED")
    void testBooking_StatusCancelled() {
        booking.setStatus("CANCELLED");
        assertEquals("CANCELLED", booking.getStatus());
    }
}
