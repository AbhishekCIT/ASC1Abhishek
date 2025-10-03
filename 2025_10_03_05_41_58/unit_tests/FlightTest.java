package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight entity.
 * Covers constructors, builder, getters, setters, equals, hashCode, and toString.
 */
class FlightTest {
    private Flight flight;
    private LocalDateTime dep;
    private LocalDateTime arr;
    private BigDecimal price;

    @BeforeEach
    void setUp() {
        dep = LocalDateTime.now();
        arr = dep.plusHours(5);
        price = BigDecimal.valueOf(350);
        flight = new Flight("DL123", "Delta", "JFK", "LAX", dep, arr, price);
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        assertEquals("DL123", flight.getFlightId());
        assertEquals("Delta", flight.getAirline());
        assertEquals("JFK", flight.getOrigin());
        assertEquals("LAX", flight.getDestination());
        assertEquals(dep, flight.getDeparture());
        assertEquals(arr, flight.getArrival());
        assertEquals(price, flight.getPrice());
    }

    @Test
    @DisplayName("Test setters and no-args constructor")
    void testSettersAndNoArgsConstructor() {
        Flight f = new Flight();
        f.setFlightId("DL999");
        f.setAirline("United");
        f.setOrigin("SFO");
        f.setDestination("SEA");
        f.setDeparture(dep);
        f.setArrival(arr);
        f.setPrice(BigDecimal.valueOf(200));
        assertEquals("DL999", f.getFlightId());
        assertEquals("United", f.getAirline());
        assertEquals("SFO", f.getOrigin());
        assertEquals("SEA", f.getDestination());
        assertEquals(dep, f.getDeparture());
        assertEquals(arr, f.getArrival());
        assertEquals(BigDecimal.valueOf(200), f.getPrice());
    }

    @Test
    @DisplayName("Test builder pattern")
    void testBuilder() {
        Flight f = Flight.builder()
                .flightId("UA777")
                .airline("United")
                .origin("ORD")
                .destination("MIA")
                .departure(dep)
                .arrival(arr)
                .price(BigDecimal.valueOf(500))
                .build();
        assertEquals("UA777", f.getFlightId());
        assertEquals("United", f.getAirline());
        assertEquals("ORD", f.getOrigin());
        assertEquals("MIA", f.getDestination());
        assertEquals(dep, f.getDeparture());
        assertEquals(arr, f.getArrival());
        assertEquals(BigDecimal.valueOf(500), f.getPrice());
    }

    @Test
    @DisplayName("Test equals and hashCode")
    void testEqualsAndHashCode() {
        Flight f1 = new Flight("DL123", "Delta", "JFK", "LAX", dep, arr, price);
        Flight f2 = new Flight("DL123", "Delta", "JFK", "LAX", dep, arr, price);
        assertEquals(f1, f2);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    @DisplayName("Test toString does not throw")
    void testToString() {
        assertDoesNotThrow(() -> flight.toString());
    }

    @Test
    @DisplayName("Test edge case: null fields")
    void testNullFields() {
        Flight f = new Flight(null, null, null, null, null, null, null);
        assertNull(f.getFlightId());
        assertNull(f.getAirline());
        assertNull(f.getOrigin());
        assertNull(f.getDestination());
        assertNull(f.getDeparture());
        assertNull(f.getArrival());
        assertNull(f.getPrice());
    }
}
