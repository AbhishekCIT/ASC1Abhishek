package com.airline.flightbooking.dto;

import com.airline.flightbooking.model.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRequest DTO.
 * Covers normal and edge cases for data transfer object.
 */
class BookingRequestTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testSettersAndGetters() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("XY123");
        Passenger passenger = Passenger.builder().passengerId("P1").name("John Doe").contact("1234567890").id("ID123").build();
        request.setPassenger(passenger);
        assertEquals("XY123", request.getFlightId());
        assertEquals(passenger, request.getPassenger());
    }

    /**
     * Test edge case: null passenger.
     */
    @Test
    void testNullPassenger() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("XY123");
        request.setPassenger(null);
        assertNull(request.getPassenger());
    }
}
