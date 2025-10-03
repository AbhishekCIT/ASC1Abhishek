package com.example.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking entity.
 * Covers builder, getters/setters, equals/hashCode, and edge cases.
 */
class BookingTest {

    @Test
    @DisplayName("Should create Booking using builder and access fields")
    void testBuilderAndGetters() {
        User user = User.builder().userId("U1").name("Alice").email("alice@example.com").passwordHash("hash").build();
        Flight flight = Flight.builder().flightId("F123").airline("Airline").origin("JFK").destination("LAX")
                .departureTime(LocalDateTime.now()).arrivalTime(LocalDateTime.now().plusHours(6))
                .baseFare(BigDecimal.valueOf(300)).flightClass("Economy").build();
        LocalDateTime now = LocalDateTime.now();
        Booking booking = Booking.builder()
                .bookingId("B456")
                .user(user)
                .flight(flight)
                .bookingDate(now)
                .totalPrice(BigDecimal.valueOf(350))
                .status("CONFIRMED")
                .build();
        assertEquals("B456", booking.getBookingId());
        assertEquals(user, booking.getUser());
        assertEquals(flight, booking.getFlight());
        assertEquals(now, booking.getBookingDate());
        assertEquals(BigDecimal.valueOf(350), booking.getTotalPrice());
        assertEquals("CONFIRMED", booking.getStatus());
    }

    @Test
    @DisplayName("Should set and get fields via setters and getters")
    void testSettersAndGetters() {
        Booking booking = new Booking();
        booking.setBookingId("B789");
        booking.setStatus("CANCELLED");
        assertEquals("B789", booking.getBookingId());
        assertEquals("CANCELLED", booking.getStatus());
    }

    @Test
    @DisplayName("Should test equals and hashCode contract")
    void testEqualsAndHashCode() {
        Booking b1 = Booking.builder().bookingId("B1").status("CONFIRMED").build();
        Booking b2 = Booking.builder().bookingId("B1").status("CONFIRMED").build();
        Booking b3 = Booking.builder().bookingId("B2").status("CANCELLED").build();
        assertEquals(b1, b2);
        assertEquals(b1.hashCode(), b2.hashCode());
        assertNotEquals(b1, b3);
    }

    @Test
    @DisplayName("Should handle null fields (edge case)")
    void testNullFields() {
        Booking booking = new Booking();
        assertNull(booking.getBookingId());
        assertNull(booking.getUser());
        assertNull(booking.getFlight());
        assertNull(booking.getBookingDate());
        assertNull(booking.getTotalPrice());
        assertNull(booking.getStatus());
    }
}
