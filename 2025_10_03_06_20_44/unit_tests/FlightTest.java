package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Flight entity.
 * Purpose: Verify builder, getters/setters, equals/hashCode, and edge/boundary cases.
 */
public class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = Flight.builder()
                .id(1)
                .airline("TestAir")
                .fromAirport("NYC")
                .toAirport("LAX")
                .departure(LocalDateTime.of(2025, 12, 1, 10, 0))
                .arrival(LocalDateTime.of(2025, 12, 1, 14, 0))
                .price(BigDecimal.valueOf(199.99))
                .seatsAvailable(100)
                .build();
    }

    /**
     * Test: Builder and getters
     */
    @Test
    void testBuilderAndGetters() {
        assertEquals(1, flight.getId());
        assertEquals("TestAir", flight.getAirline());
        assertEquals("NYC", flight.getFromAirport());
        assertEquals("LAX", flight.getToAirport());
        assertEquals(LocalDateTime.of(2025, 12, 1, 10, 0), flight.getDeparture());
        assertEquals(LocalDateTime.of(2025, 12, 1, 14, 0), flight.getArrival());
        assertEquals(BigDecimal.valueOf(199.99), flight.getPrice());
        assertEquals(100, flight.getSeatsAvailable());
    }

    /**
     * Test: Setters
     */
    @Test
    void testSetters() {
        flight.setSeatsAvailable(50);
        assertEquals(50, flight.getSeatsAvailable());
    }

    /**
     * Test: Equals and hashCode
     */
    @Test
    void testEqualsAndHashCode() {
        Flight flight2 = Flight.builder()
                .id(1)
                .airline("TestAir")
                .fromAirport("NYC")
                .toAirport("LAX")
                .departure(LocalDateTime.of(2025, 12, 1, 10, 0))
                .arrival(LocalDateTime.of(2025, 12, 1, 14, 0))
                .price(BigDecimal.valueOf(199.99))
                .seatsAvailable(100)
                .build();
        assertEquals(flight, flight2);
        assertEquals(flight.hashCode(), flight2.hashCode());
    }

    /**
     * Test: Edge case - null fields
     */
    @Test
    void testNullFields() {
        Flight nullFlight = new Flight();
        assertNull(nullFlight.getAirline());
        assertNull(nullFlight.getFromAirport());
        assertNull(nullFlight.getToAirport());
        assertNull(nullFlight.getDeparture());
        assertNull(nullFlight.getArrival());
        assertNull(nullFlight.getPrice());
        assertNull(nullFlight.getSeatsAvailable());
    }

    /**
     * Test: Boundary case - zero seats available
     */
    @Test
    void testZeroSeatsAvailable() {
        flight.setSeatsAvailable(0);
        assertEquals(0, flight.getSeatsAvailable());
    }

    /**
     * Test: Boundary case - negative price
     */
    @Test
    void testNegativePrice() {
        flight.setPrice(BigDecimal.valueOf(-10));
        assertEquals(BigDecimal.valueOf(-10), flight.getPrice());
    }
}
