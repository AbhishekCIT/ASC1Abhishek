package com.airtransport.entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightEntity.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class FlightEntityTest {
    private FlightEntity flightEntity;

    @BeforeEach
    void setUp() {
        flightEntity = new FlightEntity();
    }

    /**
     * Test setting and getting flightId (normal case).
     */
    @Test
    void testFlightId_Normal() {
        flightEntity.setFlightId("F123");
        assertEquals("F123", flightEntity.getFlightId());
    }

    /**
     * Test setting and getting airline (normal case).
     */
    @Test
    void testAirline_Normal() {
        flightEntity.setAirline("Delta");
        assertEquals("Delta", flightEntity.getAirline());
    }

    /**
     * Test setting and getting origin (normal case).
     */
    @Test
    void testOrigin_Normal() {
        flightEntity.setOrigin("JFK");
        assertEquals("JFK", flightEntity.getOrigin());
    }

    /**
     * Test setting and getting destination (normal case).
     */
    @Test
    void testDestination_Normal() {
        flightEntity.setDestination("LAX");
        assertEquals("LAX", flightEntity.getDestination());
    }

    /**
     * Test setting and getting date (normal case).
     */
    @Test
    void testDate_Normal() {
        flightEntity.setDate("2025-11-01");
        assertEquals("2025-11-01", flightEntity.getDate());
    }

    /**
     * Test setting and getting time (normal case).
     */
    @Test
    void testTime_Normal() {
        flightEntity.setTime("10:00");
        assertEquals("10:00", flightEntity.getTime());
    }

    /**
     * Test setting and getting price (normal case).
     */
    @Test
    void testPrice_Normal() {
        flightEntity.setPrice(200.0);
        assertEquals(200.0, flightEntity.getPrice());
    }

    /**
     * Test setting and getting seatsAvailable (normal case).
     */
    @Test
    void testSeatsAvailable_Normal() {
        flightEntity.setSeatsAvailable(50);
        assertEquals(50, flightEntity.getSeatsAvailable());
    }

    /**
     * Test edge case: setting null values for strings.
     */
    @Test
    void testSetters_NullValues() {
        flightEntity.setFlightId(null);
        flightEntity.setAirline(null);
        flightEntity.setOrigin(null);
        flightEntity.setDestination(null);
        flightEntity.setDate(null);
        flightEntity.setTime(null);
        assertNull(flightEntity.getFlightId());
        assertNull(flightEntity.getAirline());
        assertNull(flightEntity.getOrigin());
        assertNull(flightEntity.getDestination());
        assertNull(flightEntity.getDate());
        assertNull(flightEntity.getTime());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        flightEntity.setFlightId("");
        flightEntity.setAirline("");
        flightEntity.setOrigin("");
        flightEntity.setDestination("");
        flightEntity.setDate("");
        flightEntity.setTime("");
        assertEquals("", flightEntity.getFlightId());
        assertEquals("", flightEntity.getAirline());
        assertEquals("", flightEntity.getOrigin());
        assertEquals("", flightEntity.getDestination());
        assertEquals("", flightEntity.getDate());
        assertEquals("", flightEntity.getTime());
    }

    /**
     * Test boundary case: negative price and seatsAvailable.
     */
    @Test
    void testSetters_NegativeValues() {
        flightEntity.setPrice(-1.0);
        flightEntity.setSeatsAvailable(-5);
        assertEquals(-1.0, flightEntity.getPrice());
        assertEquals(-5, flightEntity.getSeatsAvailable());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
