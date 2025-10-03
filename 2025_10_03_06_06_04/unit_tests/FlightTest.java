package com.example.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight entity.
 * Covers builder, getters/setters, equals/hashCode, and edge cases.
 */
class FlightTest {

    @Test
    @DisplayName("Should create Flight using builder and access fields")
    void testBuilderAndGetters() {
        LocalDateTime dep = LocalDateTime.now();
        LocalDateTime arr = dep.plusHours(5);
        Flight flight = Flight.builder()
                .flightId("F123")
                .airline("Airline")
                .origin("JFK")
                .destination("LAX")
                .departureTime(dep)
                .arrivalTime(arr)
                .baseFare(BigDecimal.valueOf(400))
                .flightClass("Economy")
                .build();
        assertEquals("F123", flight.getFlightId());
        assertEquals("Airline", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dep, flight.getDepartureTime());
        assertEquals(arr, flight.getArrivalTime());
        assertEquals(BigDecimal.valueOf(400), flight.getBaseFare());
        assertEquals("Economy", flight.getFlightClass());
    }

    @Test
    @DisplayName("Should set and get fields via setters and getters")
    void testSettersAndGetters() {
        Flight flight = new Flight();
        flight.setFlightId("F789");
        flight.setAirline("TestAir");
        assertEquals("F789", flight.getFlightId());
        assertEquals("TestAir", flight.getAirline());
    }

    @Test
    @DisplayName("Should test equals and hashCode contract")
    void testEqualsAndHashCode() {
        Flight f1 = Flight.builder().flightId("F1").airline("A").build();
        Flight f2 = Flight.builder().flightId("F1").airline("A").build();
        Flight f3 = Flight.builder().flightId("F2").airline("B").build();
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1, f3);
    }

    @Test
    @DisplayName("Should handle null fields (edge case)")
    void testNullFields() {
        Flight flight = new Flight();
        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDepartureTime());
        assertNull(flight.getArrivalTime());
        assertNull(flight.getBaseFare());
        assertNull(flight.getFlightClass());
    }
}
