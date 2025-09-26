package com.example.flightsearch.service;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightInventoryService.
 */
class FlightInventoryServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightInventoryService flightInventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: findFlights returns mapped responses.
     */
    @Test
    void testFindFlights_Normal() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        Flight flight = new Flight();
        flight.setFlightId(1L);
        flight.setAirline("Delta");
        flight.setPrice(100.0);
        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(LocalDate.class), anyInt(), anyString()))
                .thenReturn(Arrays.asList(flight));

        List<FlightSearchResponse> result = flightInventoryService.findFlights(request);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getFlightId());
        assertEquals("Delta", result.get(0).getAirline());
    }

    /**
     * Test edge case: no flights found.
     */
    @Test
    void testFindFlights_NoFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(LocalDate.class), anyInt(), anyString()))
                .thenReturn(Collections.emptyList());

        List<FlightSearchResponse> result = flightInventoryService.findFlights(request);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test error scenario: repository throws exception.
     */
    @Test
    void testFindFlights_RepositoryThrows() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengers(1);
        request.setFlightClass("Economy");

        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(LocalDate.class), anyInt(), anyString()))
                .thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> flightInventoryService.findFlights(request));
    }
}
