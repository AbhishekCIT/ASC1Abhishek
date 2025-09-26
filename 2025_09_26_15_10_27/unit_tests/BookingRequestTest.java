package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest model.
 */
class BookingRequestTest {

    @Test
    @DisplayName("Test getters and setters for BookingRequest")
    void testGettersAndSetters() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        PassengerInfo passengerInfo = new PassengerInfo();
        passengerInfo.setName("Jane Doe");
        request.setPassengerInfo(passengerInfo);
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMethod("CARD");
        request.setPaymentInfo(paymentInfo);

        assertEquals("F123", request.getFlightId());
        assertEquals(passengerInfo, request.getPassengerInfo());
        assertEquals(paymentInfo, request.getPaymentInfo());
    }

    @Test
    @DisplayName("Test BookingRequest with null fields")
    void testNullFields() {
        BookingRequest request = new BookingRequest();
        assertNull(request.getFlightId());
        assertNull(request.getPassengerInfo());
        assertNull(request.getPaymentInfo());
    }
}
