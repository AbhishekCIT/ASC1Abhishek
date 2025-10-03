package com.example.airbooking.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest DTO.
 */
public class BookingRequestTest {
    /**
     * Test setting and getting fields.
     */
    @Test
    void testFields() {
        BookingRequest request = new BookingRequest();
        request.setFlightId(10L);
        Passenger p1 = new Passenger();
        Passenger p2 = new Passenger();
        List<Passenger> passengers = Arrays.asList(p1, p2);
        request.setPassengerDetails(passengers);
        PaymentInfo paymentInfo = new PaymentInfo();
        request.setPaymentInfo(paymentInfo);

        assertEquals(10L, request.getFlightId());
        assertEquals(passengers, request.getPassengerDetails());
        assertEquals(paymentInfo, request.getPaymentInfo());
    }

    /**
     * Test edge case: null passenger details.
     */
    @Test
    void testNullPassengerDetails() {
        BookingRequest request = new BookingRequest();
        request.setPassengerDetails(null);
        assertNull(request.getPassengerDetails());
    }
}
