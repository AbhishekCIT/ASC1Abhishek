package com.example.flightsearch.service;

import com.example.flightsearch.client.FlightDataProviderClient;
import com.example.flightsearch.exception.ExternalAPIException;
import com.example.flightsearch.exception.NoFlightsFoundException;
import com.example.flightsearch.logger.SearchQueryLogger;
import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightFilters;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.repository.SearchQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchService.
 */
class FlightSearchServiceTest {

    @Mock
    private FlightDataProviderClient flightDataProviderClient;
    @Mock
    private SearchQueryLogger searchQueryLogger;
    @Mock
    private SearchQueryRepository searchQueryRepository;
    @InjectMocks
    private FlightSearchService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: searchFlights returns filtered and sorted flights.
     */
    @Test
    @DisplayName("searchFlights returns filtered and sorted flights")
    void testSearchFlights_NormalScenario() {
        Flight f1 = new Flight("DL123", "Delta", LocalDateTime.now(), LocalDateTime.now().plusHours(3), 450.0, 1, 5);
        Flight f2 = new Flight("UA456", "United", LocalDateTime.now(), LocalDateTime.now().plusHours(4), 470.0, 0, 3);
        List<Flight> flights = Arrays.asList(f1, f2);
        FlightFilters filters = new FlightFilters();
        filters.setAirline("Delta");
        filters.setMaxPrice(500.0);
        filters.setStops(1);
        FlightSearchRequest request = new FlightSearchRequest();
        request.setFilters(filters);
        request.setSortBy("price");
        when(flightDataProviderClient.fetchFlights(request)).thenReturn(flights);

        FlightSearchResponse response = service.searchFlights(request);
        assertNotNull(response);
        assertEquals(1, response.getFlights().size());
        assertEquals("Delta", response.getFlights().get(0).getAirline());
    }

    /**
     * Test edge case: no flights returned after filtering.
     */
    @Test
    @DisplayName("searchFlights throws NoFlightsFoundException when no flights match")
    void testSearchFlights_NoFlightsFound() {
        Flight f1 = new Flight("DL123", "Delta", LocalDateTime.now(), LocalDateTime.now().plusHours(3), 450.0, 1, 5);
        List<Flight> flights = Arrays.asList(f1);
        FlightFilters filters = new FlightFilters();
        filters.setAirline("United"); // No match
        FlightSearchRequest request = new FlightSearchRequest();
        request.setFilters(filters);
        when(flightDataProviderClient.fetchFlights(request)).thenReturn(flights);
        assertThrows(NoFlightsFoundException.class, () -> service.searchFlights(request));
    }

    /**
     * Test boundary condition: all flights have zero available seats.
     */
    @Test
    @DisplayName("searchFlights throws NoFlightsFoundException when all flights have zero seats")
    void testSearchFlights_AllZeroSeats() {
        Flight f1 = new Flight("DL123", "Delta", LocalDateTime.now(), LocalDateTime.now().plusHours(3), 450.0, 1, 0);
        List<Flight> flights = Arrays.asList(f1);
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightDataProviderClient.fetchFlights(request)).thenReturn(flights);
        assertThrows(NoFlightsFoundException.class, () -> service.searchFlights(request));
    }

    /**
     * Test error-handling: ExternalAPIException is propagated.
     */
    @Test
    @DisplayName("searchFlights propagates ExternalAPIException")
    void testSearchFlights_ExternalAPIException() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightDataProviderClient.fetchFlights(request)).thenThrow(new ExternalAPIException("API error"));
        assertThrows(ExternalAPIException.class, () -> service.searchFlights(request));
    }

    /**
     * Test normal scenario: searchFlights logs the query.
     */
    @Test
    @DisplayName("searchFlights logs the search query")
    void testSearchFlights_LogsQuery() {
        Flight f1 = new Flight("DL123", "Delta", LocalDateTime.now(), LocalDateTime.now().plusHours(3), 450.0, 1, 5);
        List<Flight> flights = Arrays.asList(f1);
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightDataProviderClient.fetchFlights(request)).thenReturn(flights);
        service.searchFlights(request);
        verify(searchQueryLogger, times(1)).log(request);
    }
}
