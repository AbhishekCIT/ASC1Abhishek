package com.airtransport.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightSearchRequest model.
 */
public class FlightSearchRequestTest {
    private FlightSearchRequest request;

    @BeforeEach
    void setUp() {
        request = new FlightSearchRequest();
    }

    @AfterEach
    void tearDown() {
        request = null;
    }

    @Test
    @DisplayName("Test setters and getters for normal values")
    void testSettersAndGetters_NormalValues() {
        LocalDate date = LocalDate.of(2025, 12, 1);
        Map<String, Object> filters = new HashMap<>();
        filters.put("airline", "Delta");
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(date);
        request.setPassengerCount(2);
        request.setFilters(filters);
        request.setUserId("user123");

        assertEquals("JFK", request.getSource());
        assertEquals("LAX", request.getDestination());
        assertEquals(date, request.getDate());
        assertEquals(2, request.getPassengerCount());
        assertEquals(filters, request.getFilters());
        assertEquals("user123", request.getUserId());
    }

    @Test
    @DisplayName("Test setters and getters with edge cases (nulls and boundary values)")
    void testSettersAndGetters_EdgeCases() {
        request.setSource(null);
        request.setDestination("");
        request.setDate(null);
        request.setPassengerCount(0);
        request.setFilters(null);
        request.setUserId(null);

        assertNull(request.getSource());
        assertEquals("", request.getDestination());
        assertNull(request.getDate());
        assertEquals(0, request.getPassengerCount());
        assertNull(request.getFilters());
        assertNull(request.getUserId());
    }

    @Test
    @DisplayName("Test passengerCount setter with negative value (boundary condition)")
    void testSetPassengerCount_NegativeValue() {
        request.setPassengerCount(-1);
        assertEquals(-1, request.getPassengerCount(), "Passenger count should accept negative values (no validation in model)");
    }
}
