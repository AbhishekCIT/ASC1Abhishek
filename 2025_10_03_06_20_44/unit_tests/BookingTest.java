package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Booking entity.
 * Purpose: Verify builder, getters/setters, equals/hashCode, and edge cases.
 */
public class BookingTest {
    private Booking booking;
    private User user;
    private Flight flight;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1).name("Test User").email("test@example.com").build();
        flight = Flight.builder().id(1).flightNumber("AB123").origin("NYC").destination("LAX").build();
        booking = Booking.builder()
                .id(1)
                .user(user)
                .flight(flight)
                .status("CONFIRMED")
                .confirmationNumber("CONF123456")
                .bookedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Test: Builder and getters
     */
    @Test
    void testBuilderAndGetters() {
        assertEquals(1, booking.getId());
        assertEquals(user, booking.getUser());
        assertEquals(flight, booking.getFlight());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals("CONF123456", booking.getConfirmationNumber());
        assertNotNull(booking.getBookedAt());
    }

    /**
     * Test: Setters
     */
    @Test
    void testSetters() {
        booking.setStatus("CANCELLED");
        assertEquals("CANCELLED", booking.getStatus());
    }

    /**
     * Test: Equals and hashCode
     */
    @Test
    void testEqualsAndHashCode() {
        Booking booking2 = Booking.builder()
                .id(1)
                .user(user)
                .flight(flight)
                .status("CONFIRMED")
                .confirmationNumber("CONF123456")
                .bookedAt(booking.getBookedAt())
                .build();
        assertEquals(booking, booking2);
        assertEquals(booking.hashCode(), booking2.hashCode());
    }

    /**
     * Test: Edge case - null fields
     */
    @Test
    void testNullFields() {
        Booking nullBooking = new Booking();
        assertNull(nullBooking.getUser());
        assertNull(nullBooking.getFlight());
        assertNull(nullBooking.getStatus());
        assertNull(nullBooking.getConfirmationNumber());
        assertNull(nullBooking.getBookedAt());
    }

    /**
     * Test: Boundary case - empty confirmation number
     */
    @Test
    void testEmptyConfirmationNumber() {
        booking.setConfirmationNumber("");
        assertEquals("", booking.getConfirmationNumber());
    }
}
