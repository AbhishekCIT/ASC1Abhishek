package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @Test
    @DisplayName("Should set and get id correctly")
    void testIdGetterSetter() {
        flight.setId(111L);
        assertEquals(111L, flight.getId());
    }

    @Test
    @DisplayName("Should set and get airline correctly")
    void testAirlineGetterSetter() {
        flight.setAirline("United");
        assertEquals("United", flight.getAirline());
    }

    @Test
    @DisplayName("Should set and get fromAirport correctly")
    void testFromAirportGetterSetter() {
        flight.setFromAirport("JFK");
        assertEquals("JFK", flight.getFromAirport());
    }

    @Test
    @DisplayName("Should set and get toAirport correctly")
    void testToAirportGetterSetter() {
        flight.setToAirport("LAX");
        assertEquals("LAX", flight.getToAirport());
    }

    @Test
    @DisplayName("Should set and get date correctly")
    void testDateGetterSetter() {
        LocalDate date = LocalDate.of(2025, 10, 1);
        flight.setDate(date);
        assertEquals(date, flight.getDate());
    }

    @Test
    @DisplayName("Should set and get availableSeats correctly")
    void testAvailableSeatsGetterSetter() {
        flight.setAvailableSeats(7);
        assertEquals(7, flight.getAvailableSeats());
    }

    @Test
    @DisplayName("Should set and get price correctly")
    void testPriceGetterSetter() {
        flight.setPrice(399.99);
        assertEquals(399.99, flight.getPrice());
    }

    @Test
    @DisplayName("Should handle null values for fields")
    void testNullFields() {
        flight.setAirline(null);
        flight.setFromAirport(null);
        flight.setToAirport(null);
        flight.setDate(null);
        flight.setAvailableSeats(null);
        flight.setPrice(null);
        assertNull(flight.getAirline());
        assertNull(flight.getFromAirport());
        assertNull(flight.getToAirport());
        assertNull(flight.getDate());
        assertNull(flight.getAvailableSeats());
        assertNull(flight.getPrice());
    }
}
