package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookFlightRequest and its nested classes.
 * Covers constructors, getters/setters, and edge cases.
 */
class BookFlightRequestTest {

    /**
     * Purpose: Test setting and getting all fields in BookFlightRequest.
     */
    @Test
    void testSettersAndGetters() {
        BookFlightRequest request = new BookFlightRequest();
        request.setFlightId("FL123");
        BookFlightRequest.Passenger passenger = new BookFlightRequest.Passenger();
        passenger.setName("John Doe");
        passenger.setEmail("john@example.com");
        request.setPassenger(passenger);
        BookFlightRequest.Payment payment = new BookFlightRequest.Payment();
        payment.setToken("tok_abc");
        request.setPayment(payment);

        assertEquals("FL123", request.getFlightId());
        assertEquals(passenger, request.getPassenger());
        assertEquals(payment, request.getPayment());
        assertEquals("John Doe", request.getPassenger().getName());
        assertEquals("john@example.com", request.getPassenger().getEmail());
        assertEquals("tok_abc", request.getPayment().getToken());
    }

    /**
     * Purpose: Test edge case with null values for all fields.
     */
    @Test
    void testNullValues() {
        BookFlightRequest request = new BookFlightRequest();
        request.setFlightId(null);
        request.setPassenger(null);
        request.setPayment(null);
        assertNull(request.getFlightId());
        assertNull(request.getPassenger());
        assertNull(request.getPayment());
    }

    /**
     * Purpose: Test nested Passenger class with blank values.
     */
    @Test
    void testPassengerBlankValues() {
        BookFlightRequest.Passenger passenger = new BookFlightRequest.Passenger();
        passenger.setName("");
        passenger.setEmail("");
        assertEquals("", passenger.getName());
        assertEquals("", passenger.getEmail());
    }

    /**
     * Purpose: Test nested Payment class with blank token.
     */
    @Test
    void testPaymentBlankToken() {
        BookFlightRequest.Payment payment = new BookFlightRequest.Payment();
        payment.setToken("");
        assertEquals("", payment.getToken());
    }
}
