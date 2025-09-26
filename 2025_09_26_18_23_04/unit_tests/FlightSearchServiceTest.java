package com.example.flightsearch.service;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.util.LoggingUtil;
import com.example.flightsearch.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchService.
 */
class FlightSearchServiceTest {
    @Mock
    private FlightInventoryService flightInventoryService;
    @Mock
    private AirlineApiIntegrationService airlineApiIntegrationService;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private LoggingUtil loggingUtil;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: searchFlights returns merged flights.
     */
    @Test
    void testSearchFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse dbFlight = new FlightSearchResponse();
        dbFlight.setFlightId(1L);
        dbFlight.setAirline("Delta");
        FlightSearchResponse apiFlight = new FlightSearchResponse();
        apiFlight.setFlightId(2L);
        apiFlight.setAirline("United");

        when(flightInventoryService.findFlights(any(FlightSearchRequest.class))).thenReturn(Arrays.asList(dbFlight));
        when(airlineApiIntegrationService.syncFlights(any(FlightSearchRequest.class))).thenReturn(Arrays.asList(apiFlight));

        List<FlightSearchResponse> result = flightSearchService.searchFlights(request);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(f -> f.getFlightId() == 1L));
        assertTrue(result.stream().anyMatch(f -> f.getFlightId() == 2L));
        verify(validationUtil, times(1)).validateSearchParams(request);
        verify(loggingUtil, times(1)).logSearch(eq(request), any());
    }

    /**
     * Test edge case: duplicate flightId in db and API (should deduplicate).
     */
    @Test
    void testMergeAndFilter_DuplicateFlightId() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse dbFlight = new FlightSearchResponse();
        dbFlight.setFlightId(1L);
        dbFlight.setAirline("Delta");
        FlightSearchResponse apiFlight = new FlightSearchResponse();
        apiFlight.setFlightId(1L);
        apiFlight.setAirline("Delta");

        when(flightInventoryService.findFlights(any(FlightSearchRequest.class))).thenReturn(Arrays.asList(dbFlight));
        when(airlineApiIntegrationService.syncFlights(any(FlightSearchRequest.class))).thenReturn(Arrays.asList(apiFlight));

        List<FlightSearchResponse> result = flightSearchService.searchFlights(request);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getFlightId());
    }

    /**
     * Test edge case: no flights in either source.
     */
    @Test
    void testSearchFlights_NoFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightInventoryService.findFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());
        when(airlineApiIntegrationService.syncFlights(any(FlightSearchRequest.class))).thenReturn(Collections.emptyList());

        List<FlightSearchResponse> result = flightSearchService.searchFlights(request);
        assertTrue(result.isEmpty());
    }

    /**
     * Test error scenario: validation fails (throws IllegalArgumentException).
     */
    @Test
    void testSearchFlights_ValidationFails() {
        FlightSearchRequest request = new FlightSearchRequest();
        doThrow(new IllegalArgumentException("Invalid input")).when(validationUtil).validateSearchParams(request);
        assertThrows(IllegalArgumentException.class, () -> flightSearchService.searchFlights(request));
    }

    /**
     * Test error scenario: downstream service throws exception.
     */
    @Test
    void testSearchFlights_DownstreamThrows() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightInventoryService.findFlights(any(FlightSearchRequest.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> flightSearchService.searchFlights(request));
    }
}
