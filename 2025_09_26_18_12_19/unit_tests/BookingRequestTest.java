package com.example.flightbooking.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest model.
 */
class BookingRequestTest {
    private BookingRequest request;

    @BeforeEach
    void setUp() {
        request = new BookingRequest();
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    void testFlightId() {
        request.setFlightId("F123");
        assertEquals("F123", request.getFlightId());
    }

    /**
     * Test setting and getting userId.
     */
    @Test
    void testUserId() {
        request.setUserId("U456");
        assertEquals("U456", request.getUserId());
    }

    /**
     * Test setting and getting passengerDetails.
     */
    @Test
    void testPassengerDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("name", "John Doe");
        request.setPassengerDetails(details);
        assertEquals(details, request.getPassengerDetails());
    }

    /**
     * Test setting and getting paymentDetails.
     */
    @Test
    void testPaymentDetails() {
        PaymentDetails payment = new PaymentDetails();
        request.setPaymentDetails(payment);
        assertEquals(payment, request.getPaymentDetails());
    }

    /**
     * Test setting null values.
     */
    @Test
    void testNullValues() {
        request.setFlightId(null);
        request.setUserId(null);
        request.setPassengerDetails(null);
        request.setPaymentDetails(null);
        assertNull(request.getFlightId());
        assertNull(request.getUserId());
        assertNull(request.getPassengerDetails());
        assertNull(request.getPaymentDetails());
    }
}
