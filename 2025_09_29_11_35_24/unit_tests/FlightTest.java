package com.airtransport.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Flight model.
 */
public class FlightTest {
    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = new Flight();
    }

    @AfterEach
    void tearDown() {
        flight = null;
    }

    @Test
    @DisplayName("Test default constructor and setters/getters")
    void testDefaultConstructorAndSettersGetters() {
        flight.setAirline("Delta");
        flight.setFlightNumber("DL123");
        flight.setDepartureTime("10:00");
        flight.setArrivalTime("13:00");
        flight.setDuration("3h");
        flight.setPrice(450.0);

        assertEquals("Delta", flight.getAirline());
        assertEquals("DL123", flight.getFlightNumber());
        assertEquals("10:00", flight.getDepartureTime());
        assertEquals("13:00", flight.getArrivalTime());
        assertEquals("3h", flight.getDuration());
        assertEquals(450.0, flight.getPrice());
    }

    @Test
    @DisplayName("Test parameterized constructor")
    void testParameterizedConstructor() {
        Flight f = new Flight("United", "UA456", "11:00", "14:30", "3h 30m", 470.0);
        assertEquals("United", f.getAirline());
        assertEquals("UA456", f.getFlightNumber());
        assertEquals("11:00", f.getDepartureTime());
        assertEquals("14:30", f.getArrivalTime());
        assertEquals("3h 30m", f.getDuration());
        assertEquals(470.0, f.getPrice());
    }

    @Test
    @DisplayName("Test setters and getters with edge cases (nulls and boundary values)")
    void testSettersGetters_EdgeCases() {
        flight.setAirline(null);
        flight.setFlightNumber("");
        flight.setDepartureTime(null);
        flight.setArrivalTime("");
        flight.setDuration(null);
        flight.setPrice(-1.0);

        assertNull(flight.getAirline());
        assertEquals("", flight.getFlightNumber());
        assertNull(flight.getDepartureTime());
        assertEquals("", flight.getArrivalTime());
        assertNull(flight.getDuration());
        assertEquals(-1.0, flight.getPrice());
    }
}
