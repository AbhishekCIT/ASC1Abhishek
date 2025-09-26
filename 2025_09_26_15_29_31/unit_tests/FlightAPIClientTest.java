package com.example.flightsearch.client;

import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for FlightAPIClient.
 */
public class FlightAPIClientTest {
    private FlightAPIClient flightAPIClient;

    @BeforeEach
    void setUp() {
        flightAPIClient = new FlightAPIClient();
    }

    @Test
    @DisplayName("Should return mock flight for JFK to LAX")
    // Tests normal scenario where origin and destination match mock data
    void testQueryFlights_ReturnsMockFlight() throws ExternalAPIException {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        List<Flight> flights = flightAPIClient.queryFlights(request);
        assertNotNull(flights);
        assertEquals(1, flights.size());
        Flight flight = flights.get(0);
        assertEquals("DL123", flight.getFlightNumber());
        assertEquals("Delta", flight.getAirline());
        assertEquals("2024-07-01T08:00:00", flight.getDepartureTime());
        assertEquals("2024-07-01T11:00:00", flight.getArrivalTime());
        assertEquals(350, flight.getPrice());
        assertEquals("3h", flight.getDuration());
        assertEquals(0, flight.getStops());
    }

    @Test
    @DisplayName("Should return empty list for non-matching route")
    // Tests edge case where origin and destination do not match mock data
    void testQueryFlights_ReturnsEmptyList() throws ExternalAPIException {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("ORD");
        request.setDestination("SFO");
        List<Flight> flights = flightAPIClient.queryFlights(request);
        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }

    @Test
    @DisplayName("Should handle null origin and destination gracefully")
    // Tests error-handling scenario with null values
    void testQueryFlights_NullOriginDestination() throws ExternalAPIException {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin(null);
        request.setDestination(null);
        List<Flight> flights = flightAPIClient.queryFlights(request);
        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }

    @Test
    @DisplayName("Should handle empty origin and destination gracefully")
    // Tests error-handling scenario with empty strings
    void testQueryFlights_EmptyOriginDestination() throws ExternalAPIException {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("");
        request.setDestination("");
        List<Flight> flights = flightAPIClient.queryFlights(request);
        assertNotNull(flights);
        assertTrue(flights.isEmpty());
    }
}
