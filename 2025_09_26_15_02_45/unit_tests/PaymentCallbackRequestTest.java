package com.example.airbooking.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCallbackRequestTest {
    @Test
    @DisplayName("Test all getters and setters for PaymentCallbackRequest")
    void testGettersAndSetters() {
        PaymentCallbackRequest req = new PaymentCallbackRequest();
        req.setTransactionId("TXN123");
        req.setStatus("SUCCESS");
        req.setBookingRef("ABC123");
        assertEquals("TXN123", req.getTransactionId());
        assertEquals("SUCCESS", req.getStatus());
        assertEquals("ABC123", req.getBookingRef());
    }

    @Test
    @DisplayName("Test PaymentCallbackRequest with null values")
    void testNullValues() {
        PaymentCallbackRequest req = new PaymentCallbackRequest();
        req.setTransactionId(null);
        req.setStatus(null);
        req.setBookingRef(null);
        assertNull(req.getTransactionId());
        assertNull(req.getStatus());
        assertNull(req.getBookingRef());
    }
}
