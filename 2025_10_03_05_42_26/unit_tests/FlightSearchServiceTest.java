package com.airtransport.service;

import com.airtransport.model.Flight;
import com.airtransport.util.AirportCodeValidatorUtil;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for FlightSearchService.
 * Covers normal, edge, boundary, and error scenarios for searchFlights.
 */
class FlightSearchServiceTest {
    private FlightSearchService flightSearchService;

    @BeforeEach
    void setUp() {
        flightSearchService = new FlightSearchService();
    }

    /**
     * Test searching flights with valid input (normal case).
     */
    @Test
    void testSearchFlights_Normal() {
        try (MockedStatic<AirportCodeValidatorUtil> util = mockStatic(AirportCodeValidatorUtil.class)) {
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("JFK")).thenReturn(true);
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("LAX")).thenReturn(true);
            String date = LocalDate.now().plusDays(1).toString();
            List<Flight> flights = flightSearchService.searchFlights("JFK", "LAX", date);
            assertNotNull(flights);
            assertFalse(flights.isEmpty());
            assertEquals("JFK", flights.get(0).getOrigin());
            assertEquals("LAX", flights.get(0).getDestination());
        }
    }

    /**
     * Test searching flights with invalid origin airport code (error scenario).
     */
    @Test
    void testSearchFlights_InvalidOrigin() {
        try (MockedStatic<AirportCodeValidatorUtil> util = mockStatic(AirportCodeValidatorUtil.class)) {
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("XXX")).thenReturn(false);
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("LAX")).thenReturn(true);
            String date = LocalDate.now().plusDays(1).toString();
            Exception ex = assertThrows(RuntimeException.class, () -> flightSearchService.searchFlights("XXX", "LAX", date));
            assertEquals("Invalid origin airport code", ex.getMessage());
        }
    }

    /**
     * Test searching flights with invalid destination airport code (error scenario).
     */
    @Test
    void testSearchFlights_InvalidDestination() {
        try (MockedStatic<AirportCodeValidatorUtil> util = mockStatic(AirportCodeValidatorUtil.class)) {
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("JFK")).thenReturn(true);
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("XXX")).thenReturn(false);
            String date = LocalDate.now().plusDays(1).toString();
            Exception ex = assertThrows(RuntimeException.class, () -> flightSearchService.searchFlights("JFK", "XXX", date));
            assertEquals("Invalid destination airport code", ex.getMessage());
        }
    }

    /**
     * Test searching flights with date in the past (error scenario).
     */
    @Test
    void testSearchFlights_DateInPast() {
        try (MockedStatic<AirportCodeValidatorUtil> util = mockStatic(AirportCodeValidatorUtil.class)) {
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("JFK")).thenReturn(true);
            util.when(() -> AirportCodeValidatorUtil.isValidAirportCode("LAX")).thenReturn(true);
            String date = LocalDate.now().minusDays(1).toString();
            Exception ex = assertThrows(RuntimeException.class, () -> flightSearchService.searchFlights("JFK", "LAX", date));
            assertEquals("Date cannot be in the past", ex.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
