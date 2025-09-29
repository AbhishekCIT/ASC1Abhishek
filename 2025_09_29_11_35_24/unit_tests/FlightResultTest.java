package com.airtransport.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightResult entity.
 */
public class FlightResultTest {
    private FlightResult flightResult;

    @BeforeEach
    void setUp() {
        flightResult = new FlightResult();
    }

    @AfterEach
    void tearDown() {
        flightResult = null;
    }

    @Test
    @DisplayName("Test all getters and setters for normal values")
    void testGettersAndSetters_NormalValues() {
        flightResult.setId("id123");
        flightResult.setSearchQueryId("query456");
        flightResult.setAirline("Delta");
        flightResult.setFlightNumber("DL789");
        flightResult.setDepartureTime("10:00");
        flightResult.setArrivalTime("13:00");
        flightResult.setDuration("3h");
        flightResult.setPrice(299.99);

        assertEquals("id123", flightResult.getId());
        assertEquals("query456", flightResult.getSearchQueryId());
        assertEquals("Delta", flightResult.getAirline());
        assertEquals("DL789", flightResult.getFlightNumber());
        assertEquals("10:00", flightResult.getDepartureTime());
        assertEquals("13:00", flightResult.getArrivalTime());
        assertEquals("3h", flightResult.getDuration());
        assertEquals(299.99, flightResult.getPrice());
    }

    @Test
    @DisplayName("Test setters and getters with edge cases (empty and null values)")
    void testGettersAndSetters_EdgeCases() {
        flightResult.setId("");
        flightResult.setSearchQueryId(null);
        flightResult.setAirline("");
        flightResult.setFlightNumber(null);
        flightResult.setDepartureTime("");
        flightResult.setArrivalTime(null);
        flightResult.setDuration("");
        flightResult.setPrice(0.0);

        assertEquals("", flightResult.getId());
        assertNull(flightResult.getSearchQueryId());
        assertEquals("", flightResult.getAirline());
        assertNull(flightResult.getFlightNumber());
        assertEquals("", flightResult.getDepartureTime());
        assertNull(flightResult.getArrivalTime());
        assertEquals("", flightResult.getDuration());
        assertEquals(0.0, flightResult.getPrice());
    }

    @Test
    @DisplayName("Test price setter with negative value (boundary condition)")
    void testSetPrice_NegativeValue() {
        flightResult.setPrice(-100.0);
        assertEquals(-100.0, flightResult.getPrice(), "Price should accept negative values (no validation in entity)");
    }
}
