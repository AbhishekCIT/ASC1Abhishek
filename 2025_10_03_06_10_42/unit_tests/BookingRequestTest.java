package com.airtransport.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest DTO.
 * Covers normal, edge, and boundary cases for getters and setters.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
    }

    /**
     * Test setting and getting all fields with normal values.
     */
    @Test
    @DisplayName("BookingRequest: should set and get all fields correctly")
    void testSettersAndGetters_Normal() {
        Long flightId = 123L;
        List<PassengerDTO> passengers = new ArrayList<>();
        passengers.add(new PassengerDTO());
        List<String> seats = List.of("12A", "12B");
        String paymentMethod = "credit_card";
        Map<String, Object> paymentInfo = new HashMap<>();
        paymentInfo.put("cardNumber", "****");

        bookingRequest.setFlightId(flightId);
        bookingRequest.setPassengerDetails(passengers);
        bookingRequest.setSeatSelection(seats);
        bookingRequest.setPaymentMethod(paymentMethod);
        bookingRequest.setPaymentInfo(paymentInfo);

        assertEquals(flightId, bookingRequest.getFlightId());
        assertEquals(passengers, bookingRequest.getPassengerDetails());
        assertEquals(seats, bookingRequest.getSeatSelection());
        assertEquals(paymentMethod, bookingRequest.getPaymentMethod());
        assertEquals(paymentInfo, bookingRequest.getPaymentInfo());
    }

    /**
     * Test setting fields to null (edge case).
     */
    @Test
    @DisplayName("BookingRequest: should handle null values in setters")
    void testSetters_NullValues() {
        bookingRequest.setFlightId(null);
        bookingRequest.setPassengerDetails(null);
        bookingRequest.setSeatSelection(null);
        bookingRequest.setPaymentMethod(null);
        bookingRequest.setPaymentInfo(null);

        assertNull(bookingRequest.getFlightId());
        assertNull(bookingRequest.getPassengerDetails());
        assertNull(bookingRequest.getSeatSelection());
        assertNull(bookingRequest.getPaymentMethod());
        assertNull(bookingRequest.getPaymentInfo());
    }

    /**
     * Test empty lists and maps (boundary condition).
     */
    @Test
    @DisplayName("BookingRequest: should handle empty lists and maps")
    void testSetters_EmptyCollections() {
        bookingRequest.setPassengerDetails(new ArrayList<>());
        bookingRequest.setSeatSelection(new ArrayList<>());
        bookingRequest.setPaymentInfo(new HashMap<>());

        assertTrue(bookingRequest.getPassengerDetails().isEmpty());
        assertTrue(bookingRequest.getSeatSelection().isEmpty());
        assertTrue(bookingRequest.getPaymentInfo().isEmpty());
    }
}
