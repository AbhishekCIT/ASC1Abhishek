package com.example.flightbooking.service;

import com.example.flightbooking.model.Flight;
import com.example.flightbooking.model.SearchCriteria;
import com.example.flightbooking.repository.FlightRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchService.
 */
class FlightSearchServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightSearchService flightSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test searchFlights - normal scenario")
    void testSearchFlights_Normal() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        criteria.setOrigin("NYC");
        criteria.setDestination("LAX");
        criteria.setDepartureDate(LocalDate.now().plusDays(10));
        criteria.setReturnDate(LocalDate.now().plusDays(20));
        criteria.setFlightClass("Economy");
        criteria.setPassengers(2);
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        List<Flight> flights = Arrays.asList(flight1, flight2);
        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(), any(), anyString(), anyInt())).thenReturn(flights);

        // Act
        List<Flight> result = flightSearchService.searchFlights(criteria);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test searchFlights - no flights found (empty list)")
    void testSearchFlights_EmptyList() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(), any(), anyString(), anyInt())).thenReturn(Collections.emptyList());

        // Act
        List<Flight> result = flightSearchService.searchFlights(criteria);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test searchFlights - edge case: null criteria fields")
    void testSearchFlights_NullCriteriaFields() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria(); // all fields null
        when(flightRepository.findAvailableFlights(null, null, null, null, null, 0)).thenReturn(Collections.emptyList());

        // Act
        List<Flight> result = flightSearchService.searchFlights(criteria);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test searchFlights - boundary case: 0 passengers")
    void testSearchFlights_ZeroPassengers() {
        // Arrange
        SearchCriteria criteria = new SearchCriteria();
        criteria.setPassengers(0);
        when(flightRepository.findAvailableFlights(anyString(), anyString(), any(), any(), anyString(), eq(0))).thenReturn(Collections.emptyList());

        // Act
        List<Flight> result = flightSearchService.searchFlights(criteria);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
