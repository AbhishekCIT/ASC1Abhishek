package com.example.airbooking.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRequest DTO.
 * Covers normal, edge, and boundary scenarios for getters and setters.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
    }

    /**
     * Test setting and getting flightId (normal scenario).
     */
    @Test
    @DisplayName("get/setFlightId: should set and get flightId correctly")
    void setAndGetFlightId() {
        bookingRequest.setFlightId(123);
        assertEquals(123, bookingRequest.getFlightId());
    }

    /**
     * Test setting flightId to null (edge case).
     */
    @Test
    @DisplayName("setFlightId: should handle null value")
    void setFlightId_null() {
        bookingRequest.setFlightId(null);
        assertNull(bookingRequest.getFlightId());
    }

    /**
     * Test setting and getting passengerInfo (normal scenario).
     */
    @Test
    @DisplayName("get/setPassengerInfo: should set and get passengerInfo correctly")
    void setAndGetPassengerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "John Doe");
        bookingRequest.setPassengerInfo(info);
        assertEquals(info, bookingRequest.getPassengerInfo());
    }

    /**
     * Test setting passengerInfo to empty map (boundary case).
     */
    @Test
    @DisplayName("setPassengerInfo: should handle empty map")
    void setPassengerInfo_emptyMap() {
        bookingRequest.setPassengerInfo(Collections.emptyMap());
        assertTrue(bookingRequest.getPassengerInfo().isEmpty());
    }

    /**
     * Test setting passengerInfo to null (edge case).
     */
    @Test
    @DisplayName("setPassengerInfo: should handle null value")
    void setPassengerInfo_null() {
        bookingRequest.setPassengerInfo(null);
        assertNull(bookingRequest.getPassengerInfo());
    }

    /**
     * Test setting and getting paymentInfo (normal scenario).
     */
    @Test
    @DisplayName("get/setPaymentInfo: should set and get paymentInfo correctly")
    void setAndGetPaymentInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("card", "1234");
        bookingRequest.setPaymentInfo(info);
        assertEquals(info, bookingRequest.getPaymentInfo());
    }

    /**
     * Test setting paymentInfo to empty map (boundary case).
     */
    @Test
    @DisplayName("setPaymentInfo: should handle empty map")
    void setPaymentInfo_emptyMap() {
        bookingRequest.setPaymentInfo(Collections.emptyMap());
        assertTrue(bookingRequest.getPaymentInfo().isEmpty());
    }

    /**
     * Test setting paymentInfo to null (edge case).
     */
    @Test
    @DisplayName("setPaymentInfo: should handle null value")
    void setPaymentInfo_null() {
        bookingRequest.setPaymentInfo(null);
        assertNull(bookingRequest.getPaymentInfo());
    }
}
