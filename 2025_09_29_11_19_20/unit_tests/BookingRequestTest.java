package com.example.airbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRequest model.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;
    private Passenger passenger;
    private PaymentInfo paymentInfo;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
        passenger = new Passenger();
        paymentInfo = new PaymentInfo();
    }

    @Test
    @DisplayName("Test setting and getting flightId")
    void testFlightId() {
        bookingRequest.setFlightId(42L);
        assertEquals(42L, bookingRequest.getFlightId());
    }

    @Test
    @DisplayName("Test setting and getting passengerDetails")
    void testPassengerDetails() {
        bookingRequest.setPassengerDetails(passenger);
        assertSame(passenger, bookingRequest.getPassengerDetails());
    }

    @Test
    @DisplayName("Test setting and getting seat")
    void testSeat() {
        bookingRequest.setSeat("15B");
        assertEquals("15B", bookingRequest.getSeat());
    }

    @Test
    @DisplayName("Test setting and getting paymentInfo")
    void testPaymentInfo() {
        bookingRequest.setPaymentInfo(paymentInfo);
        assertSame(paymentInfo, bookingRequest.getPaymentInfo());
    }

    @Test
    @DisplayName("Test null values for fields")
    void testNullFields() {
        bookingRequest.setPassengerDetails(null);
        bookingRequest.setSeat(null);
        bookingRequest.setPaymentInfo(null);
        assertNull(bookingRequest.getPassengerDetails());
        assertNull(bookingRequest.getSeat());
        assertNull(bookingRequest.getPaymentInfo());
    }
}
