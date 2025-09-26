package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest model.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
    }

    @Test
    @DisplayName("Should set and get flightId correctly")
    void testFlightId() {
        bookingRequest.setFlightId("F123");
        assertEquals("F123", bookingRequest.getFlightId(), "FlightId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get userId correctly")
    void testUserId() {
        bookingRequest.setUserId("U456");
        assertEquals("U456", bookingRequest.getUserId(), "UserId should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should set and get paymentInfo correctly")
    void testPaymentInfo() {
        PaymentInfo paymentInfo = new PaymentInfo();
        bookingRequest.setPaymentInfo(paymentInfo);
        assertEquals(paymentInfo, bookingRequest.getPaymentInfo(), "PaymentInfo should be set and retrieved correctly");
    }

    @Test
    @DisplayName("Should handle null values for all fields")
    void testNullValues() {
        bookingRequest.setFlightId(null);
        bookingRequest.setUserId(null);
        bookingRequest.setPaymentInfo(null);
        assertNull(bookingRequest.getFlightId());
        assertNull(bookingRequest.getUserId());
        assertNull(bookingRequest.getPaymentInfo());
    }
}
