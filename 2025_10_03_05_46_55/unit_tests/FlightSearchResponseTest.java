package com.asc.airbooking.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for FlightSearchResponse and its nested FlightInfo class.
 * Covers setters, getters, and edge cases.
 */
class FlightSearchResponseTest {

    /**
     * Purpose: Test setting and getting flights list.
     */
    @Test
    void testSettersAndGetters() {
        FlightSearchResponse response = new FlightSearchResponse();
        List<FlightSearchResponse.FlightInfo> flights = new ArrayList<>();
        FlightSearchResponse.FlightInfo info = new FlightSearchResponse.FlightInfo();
        info.setFlightId("FL123");
        info.setAirline("Delta");
        info.setTime("10:00");
        info.setPrice(320.00);
        flights.add(info);
        response.setFlights(flights);
        assertEquals(flights, response.getFlights());
        assertEquals("FL123", response.getFlights().get(0).getFlightId());
        assertEquals("Delta", response.getFlights().get(0).getAirline());
        assertEquals("10:00", response.getFlights().get(0).getTime());
        assertEquals(320.00, response.getFlights().get(0).getPrice());
    }

    /**
     * Purpose: Test edge case with null flights list.
     */
    @Test
    void testNullFlightsList() {
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(null);
        assertNull(response.getFlights());
    }

    /**
     * Purpose: Test edge case with empty flights list.
     */
    @Test
    void testEmptyFlightsList() {
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(new ArrayList<>());
        assertTrue(response.getFlights().isEmpty());
    }

    /**
     * Purpose: Test nested FlightInfo class with blank and boundary values.
     */
    @Test
    void testFlightInfoBlankAndBoundaryValues() {
        FlightSearchResponse.FlightInfo info = new FlightSearchResponse.FlightInfo();
        info.setFlightId("");
        info.setAirline("");
        info.setTime("");
        info.setPrice(0.0);
        assertEquals("", info.getFlightId());
        assertEquals("", info.getAirline());
        assertEquals("", info.getTime());
        assertEquals(0.0, info.getPrice());
        info.setPrice(-100.0);
        assertEquals(-100.0, info.getPrice());
    }
}
