package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Booking model.
 */
class BookingTest {

    @Test
    @DisplayName("Test getters and setters for Booking")
    void testGettersAndSetters() {
        Booking booking = new Booking();
        booking.setBookingRef("BR123456");
        booking.setFlightId("F123");
        PassengerInfo passengerInfo = new PassengerInfo();
        passengerInfo.setName("John Doe");
        booking.setPassengerInfo(passengerInfo);
        booking.setStatus("CONFIRMED");
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(now);
        booking.setTotalPrice(350.0);
        Payment payment = new Payment();
        payment.setPaymentId("P123");
        booking.setPayment(payment);

        assertEquals("BR123456", booking.getBookingRef());
        assertEquals("F123", booking.getFlightId());
        assertEquals(passengerInfo, booking.getPassengerInfo());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(now, booking.getBookingDate());
        assertEquals(350.0, booking.getTotalPrice());
        assertEquals(payment, booking.getPayment());
    }

    @Test
    @DisplayName("Test Booking with null fields")
    void testNullFields() {
        Booking booking = new Booking();
        assertNull(booking.getBookingRef());
        assertNull(booking.getFlightId());
        assertNull(booking.getPassengerInfo());
        assertNull(booking.getStatus());
        assertNull(booking.getBookingDate());
        assertEquals(0.0, booking.getTotalPrice());
        assertNull(booking.getPayment());
    }
}
