package com.airtransport.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingResponse DTO.
 * Covers normal, edge, and boundary cases for getters and setters.
 */
class BookingResponseTest {
    private BookingResponse bookingResponse;

    @BeforeEach
    void setUp() {
        bookingResponse = new BookingResponse();
    }

    /**
     * Test setting and getting all fields with normal values.
     */
    @Test
    @DisplayName("BookingResponse: should set and get all fields correctly")
    void testSettersAndGetters_Normal() {
        String bookingReference = "ABC123";
        String status = "CONFIRMED";
        Map<String, Object> receipt = new HashMap<>();
        receipt.put("amount", 100.0);
        Map<String, Object> itinerary = new HashMap<>();
        itinerary.put("flight", "DL123");

        bookingResponse.setBookingReference(bookingReference);
        bookingResponse.setStatus(status);
        bookingResponse.setReceipt(receipt);
        bookingResponse.setItinerary(itinerary);

        assertEquals(bookingReference, bookingResponse.getBookingReference());
        assertEquals(status, bookingResponse.getStatus());
        assertEquals(receipt, bookingResponse.getReceipt());
        assertEquals(itinerary, bookingResponse.getItinerary());
    }

    /**
     * Test setting fields to null (edge case).
     */
    @Test
    @DisplayName("BookingResponse: should handle null values in setters")
    void testSetters_NullValues() {
        bookingResponse.setBookingReference(null);
        bookingResponse.setStatus(null);
        bookingResponse.setReceipt(null);
        bookingResponse.setItinerary(null);

        assertNull(bookingResponse.getBookingReference());
        assertNull(bookingResponse.getStatus());
        assertNull(bookingResponse.getReceipt());
        assertNull(bookingResponse.getItinerary());
    }

    /**
     * Test empty maps (boundary condition).
     */
    @Test
    @DisplayName("BookingResponse: should handle empty maps")
    void testSetters_EmptyMaps() {
        bookingResponse.setReceipt(new HashMap<>());
        bookingResponse.setItinerary(new HashMap<>());

        assertTrue(bookingResponse.getReceipt().isEmpty());
        assertTrue(bookingResponse.getItinerary().isEmpty());
    }
}
