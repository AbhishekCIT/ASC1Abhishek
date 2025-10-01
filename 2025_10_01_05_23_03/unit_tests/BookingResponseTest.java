package com.example.airbooking.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingResponse DTO.
 * Covers normal, edge, and boundary scenarios for getters and setters.
 */
class BookingResponseTest {
    private BookingResponse bookingResponse;

    @BeforeEach
    void setUp() {
        bookingResponse = new BookingResponse();
    }

    /**
     * Test setting and getting bookingId (normal scenario).
     */
    @Test
    @DisplayName("get/setBookingId: should set and get bookingId correctly")
    void setAndGetBookingId() {
        bookingResponse.setBookingId(456);
        assertEquals(456, bookingResponse.getBookingId());
    }

    /**
     * Test setting bookingId to null (edge case).
     */
    @Test
    @DisplayName("setBookingId: should handle null value")
    void setBookingId_null() {
        bookingResponse.setBookingId(null);
        assertNull(bookingResponse.getBookingId());
    }

    /**
     * Test setting and getting status (normal scenario).
     */
    @Test
    @DisplayName("get/setStatus: should set and get status correctly")
    void setAndGetStatus() {
        bookingResponse.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", bookingResponse.getStatus());
    }

    /**
     * Test setting status to null (edge case).
     */
    @Test
    @DisplayName("setStatus: should handle null value")
    void setStatus_null() {
        bookingResponse.setStatus(null);
        assertNull(bookingResponse.getStatus());
    }

    /**
     * Test setting and getting totalFare (normal scenario).
     */
    @Test
    @DisplayName("get/setTotalFare: should set and get totalFare correctly")
    void setAndGetTotalFare() {
        BigDecimal fare = new BigDecimal("123.45");
        bookingResponse.setTotalFare(fare);
        assertEquals(fare, bookingResponse.getTotalFare());
    }

    /**
     * Test setting totalFare to zero (boundary case).
     */
    @Test
    @DisplayName("setTotalFare: should handle zero value")
    void setTotalFare_zero() {
        bookingResponse.setTotalFare(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, bookingResponse.getTotalFare());
    }

    /**
     * Test setting totalFare to negative value (boundary case).
     */
    @Test
    @DisplayName("setTotalFare: should handle negative value")
    void setTotalFare_negative() {
        BigDecimal negative = new BigDecimal("-10.00");
        bookingResponse.setTotalFare(negative);
        assertEquals(negative, bookingResponse.getTotalFare());
    }

    /**
     * Test setting totalFare to null (edge case).
     */
    @Test
    @DisplayName("setTotalFare: should handle null value")
    void setTotalFare_null() {
        bookingResponse.setTotalFare(null);
        assertNull(bookingResponse.getTotalFare());
    }

    /**
     * Test setting and getting flightDetails (normal scenario).
     */
    @Test
    @DisplayName("get/setFlightDetails: should set and get flightDetails correctly")
    void setAndGetFlightDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("flightId", 123);
        bookingResponse.setFlightDetails(details);
        assertEquals(details, bookingResponse.getFlightDetails());
    }

    /**
     * Test setting flightDetails to empty map (boundary case).
     */
    @Test
    @DisplayName("setFlightDetails: should handle empty map")
    void setFlightDetails_emptyMap() {
        bookingResponse.setFlightDetails(Collections.emptyMap());
        assertTrue(bookingResponse.getFlightDetails().isEmpty());
    }

    /**
     * Test setting flightDetails to null (edge case).
     */
    @Test
    @DisplayName("setFlightDetails: should handle null value")
    void setFlightDetails_null() {
        bookingResponse.setFlightDetails(null);
        assertNull(bookingResponse.getFlightDetails());
    }

    /**
     * Test setting and getting error (normal scenario).
     */
    @Test
    @DisplayName("get/setError: should set and get error correctly")
    void setAndGetError() {
        bookingResponse.setError("Some error");
        assertEquals("Some error", bookingResponse.getError());
    }

    /**
     * Test setting error to null (edge case).
     */
    @Test
    @DisplayName("setError: should handle null value")
    void setError_null() {
        bookingResponse.setError(null);
        assertNull(bookingResponse.getError());
    }
}
