package com.example.flightsearch.service;

import com.example.flightsearch.entity.Flight;
import com.example.flightsearch.model.FlightDTO;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.repository.FlightRepository;
import com.example.flightsearch.util.FilterService;
import com.example.flightsearch.util.PaginationService;
import com.example.flightsearch.util.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
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
    private FlightRepository flightRepository;
    @Mock
    private FilterService filterService;
    @Mock
    private PaginationService paginationService;
    @Mock
    private ValidationService validationService;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: valid request returns paginated flights.
     */
    @Test
    @DisplayName("findFlights returns paginated and filtered flights for valid request")
    void testFindFlights_ValidRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2024, 10, 1));
        request.setPage(1);
        request.setSize(2);

        Flight flight1 = new Flight();
        flight1.setFlightId("F1");
        Flight flight2 = new Flight();
        flight2.setFlightId("F2");
        List<Flight> allFlights = Arrays.asList(flight1, flight2);
        List<Flight> filteredFlights = allFlights;
        List<Flight> paginatedFlights = Collections.singletonList(flight1);

        doNothing().when(validationService).validateSearchParams(request);
        when(flightRepository.findByOriginAndDestinationAndDate(any(), any(), any())).thenReturn(allFlights);
        when(filterService.applyFilters(allFlights, null)).thenReturn(filteredFlights);
        when(paginationService.paginateResults(filteredFlights, 1, 2)).thenReturn(paginatedFlights);
        when(paginationService.getTotalPages(filteredFlights.size(), 2)).thenReturn(1);

        FlightSearchResponse response = flightSearchService.findFlights(request);
        assertNotNull(response);
        assertEquals(1, response.getFlights().size());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getCurrentPage());
        assertNull(response.getError());
    }

    /**
     * Test edge case: no flights found.
     */
    @Test
    @DisplayName("findFlights returns empty list when no flights found")
    void testFindFlights_NoFlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2024, 10, 1));
        request.setPage(1);
        request.setSize(2);

        doNothing().when(validationService).validateSearchParams(request);
        when(flightRepository.findByOriginAndDestinationAndDate(any(), any(), any())).thenReturn(Collections.emptyList());
        when(filterService.applyFilters(Collections.emptyList(), null)).thenReturn(Collections.emptyList());
        when(paginationService.paginateResults(Collections.emptyList(), 1, 2)).thenReturn(Collections.emptyList());
        when(paginationService.getTotalPages(0, 2)).thenReturn(0);

        FlightSearchResponse response = flightSearchService.findFlights(request);
        assertNotNull(response);
        assertTrue(response.getFlights().isEmpty());
        assertEquals(0, response.getTotalPages());
        assertEquals(1, response.getCurrentPage());
        assertNull(response.getError());
    }

    /**
     * Test error scenario: validation fails and throws exception.
     */
    @Test
    @DisplayName("findFlights throws exception when validation fails")
    void testFindFlights_ValidationFails() {
        FlightSearchRequest request = new FlightSearchRequest();
        doThrow(new IllegalArgumentException("Invalid params")).when(validationService).validateSearchParams(request);
        assertThrows(IllegalArgumentException.class, () -> flightSearchService.findFlights(request));
    }

    /**
     * Test boundary case: page size is zero.
     */
    @Test
    @DisplayName("findFlights handles zero page size")
    void testFindFlights_ZeroPageSize() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.of(2024, 10, 1));
        request.setPage(1);
        request.setSize(0);

        doNothing().when(validationService).validateSearchParams(request);
        when(flightRepository.findByOriginAndDestinationAndDate(any(), any(), any())).thenReturn(Collections.emptyList());
        when(filterService.applyFilters(Collections.emptyList(), null)).thenReturn(Collections.emptyList());
        when(paginationService.paginateResults(Collections.emptyList(), 1, 0)).thenReturn(Collections.emptyList());
        when(paginationService.getTotalPages(0, 0)).thenReturn(1);

        FlightSearchResponse response = flightSearchService.findFlights(request);
        assertNotNull(response);
        assertTrue(response.getFlights().isEmpty());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getCurrentPage());
    }
}
