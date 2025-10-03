package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for Flight.
 * Covers constructors, getters/setters, normal, edge, and boundary cases.
 */
class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    /**
     * Test default constructor.
     */
    @Test
    void testDefaultConstructor() {
        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDate());
        assertNull(flight.getTime());
        assertEquals(0.0, flight.getPrice());
        assertEquals(0, flight.getSeatsAvailable());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        Flight f = new Flight("F123", "Delta", "JFK", "LAX", "2025-11-01", "10:00", 200.0, 50);
        assertEquals("F123", f.getFlightId());
        assertEquals("Delta", f.getAirline());
        assertEquals("JFK", f.getOrigin());
        assertEquals("LAX", f.getDestination());
        assertEquals("2025-11-01", f.getDate());
        assertEquals("10:00", f.getTime());
        assertEquals(200.0, f.getPrice());
        assertEquals(50, f.getSeatsAvailable());
    }

    /**
     * Test setting and getting all fields (normal case).
     */
    @Test
    void testSettersAndGetters_Normal() {
        flight.setFlightId("F456");
        flight.setAirline("United");
        flight.setOrigin("SFO");
        flight.setDestination("ORD");
        flight.setDate("2025-12-01");
        flight.setTime("15:00");
        flight.setPrice(300.0);
        flight.setSeatsAvailable(100);
        assertEquals("F456", flight.getFlightId());
        assertEquals("United", flight.getAirline());
        assertEquals("SFO", flight.getOrigin());
        assertEquals("ORD", flight.getDestination());
        assertEquals("2025-12-01", flight.getDate());
        assertEquals("15:00", flight.getTime());
        assertEquals(300.0, flight.getPrice());
        assertEquals(100, flight.getSeatsAvailable());
    }

    /**
     * Test edge case: setting null values for strings.
     */
    @Test
    void testSetters_NullValues() {
        flight.setFlightId(null);
        flight.setAirline(null);
        flight.setOrigin(null);
        flight.setDestination(null);
        flight.setDate(null);
        flight.setTime(null);
        assertNull(flight.getFlightId());
        assertNull(flight.getAirline());
        assertNull(flight.getOrigin());
        assertNull(flight.getDestination());
        assertNull(flight.getDate());
        assertNull(flight.getTime());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        flight.setFlightId("");
        flight.setAirline("");
        flight.setOrigin("");
        flight.setDestination("");
        flight.setDate("");
        flight.setTime("");
        assertEquals("", flight.getFlightId());
        assertEquals("", flight.getAirline());
        assertEquals("", flight.getOrigin());
        assertEquals("", flight.getDestination());
        assertEquals("", flight.getDate());
        assertEquals("", flight.getTime());
    }

    /**
     * Test boundary case: negative price and seatsAvailable.
     */
    @Test
    void testSetters_NegativeValues() {
        flight.setPrice(-1.0);
        flight.setSeatsAvailable(-5);
        assertEquals(-1.0, flight.getPrice());
        assertEquals(-5, flight.getSeatsAvailable());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
