package com.airtransport.service;

import com.airtransport.client.ExternalFlightApiClient;
import com.airtransport.exception.ExternalApiException;
import com.airtransport.exception.InvalidSearchCriteriaException;
import com.airtransport.exception.NoFlightsFoundException;
import com.airtransport.filter.FlightResultFilter;
import com.airtransport.model.Flight;
import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.repository.FlightSearchLogRepository;
import com.airtransport.util.LoggingService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchService.
 */
public class FlightSearchServiceTest {
    @Mock
    private ExternalFlightApiClient externalFlightApiClient;
    @Mock
    private FlightResultFilter flightResultFilter;
    @Mock
    private LoggingService loggingService;
    @Mock
    private FlightSearchLogRepository flightSearchLogRepository;

    @InjectMocks
    private FlightSearchService service;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        service = new FlightSearchService(externalFlightApiClient, flightResultFilter, loggingService, flightSearchLogRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("searchFlights returns response for valid request (normal scenario)")
    void testSearchFlights_ValidRequest() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(1);
        List<Flight> flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450)
        );
        when(externalFlightApiClient.fetchFlights(request)).thenReturn(flights);
        when(flightResultFilter.applyFilters(flights, null)).thenReturn(flights);
        doNothing().when(loggingService).logSearchQuery(request);
        doNothing().when(flightSearchLogRepository).saveSearchResult(request, flights);

        FlightSearchResponse response = service.searchFlights(request);
        assertNotNull(response);
        assertEquals(flights, response.getFlights());
    }

    @Test
    @DisplayName("searchFlights throws NoFlightsFoundException when no flights found (edge case)")
    void testSearchFlights_NoFlightsFound() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(1);
        List<Flight> flights = Arrays.asList(
                new Flight("Delta", "DL123", "10:00", "13:00", "3h", 450)
        );
        when(externalFlightApiClient.fetchFlights(request)).thenReturn(flights);
        when(flightResultFilter.applyFilters(flights, null)).thenReturn(Collections.emptyList());
        doNothing().when(loggingService).logSearchQuery(request);

        assertThrows(NoFlightsFoundException.class, () -> service.searchFlights(request));
    }

    @Test
    @DisplayName("searchFlights throws ExternalApiException when external API fails (error scenario)")
    void testSearchFlights_ExternalApiException() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(1);
        when(externalFlightApiClient.fetchFlights(request)).thenThrow(new RuntimeException("API down"));
        doNothing().when(loggingService).logSearchQuery(request);

        assertThrows(ExternalApiException.class, () -> service.searchFlights(request));
    }

    @Test
    @DisplayName("searchFlights throws InvalidSearchCriteriaException for same source and destination (boundary condition)")
    void testSearchFlights_SameSourceAndDestination() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("JFK");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(1);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
    }

    @Test
    @DisplayName("searchFlights throws InvalidSearchCriteriaException for past date (boundary condition)")
    void testSearchFlights_PastDate() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().minusDays(1));
        request.setPassengerCount(1);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
    }

    @Test
    @DisplayName("searchFlights throws InvalidSearchCriteriaException for invalid passenger count (boundary condition)")
    void testSearchFlights_InvalidPassengerCount() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(0);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
        request.setPassengerCount(100);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
    }

    @Test
    @DisplayName("searchFlights throws InvalidSearchCriteriaException for null source or destination (error scenario)")
    void testSearchFlights_NullSourceOrDestination() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setSource(null);
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        request.setPassengerCount(1);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
        request.setSource("JFK");
        request.setDestination(null);
        assertThrows(InvalidSearchCriteriaException.class, () -> service.searchFlights(request));
    }
}
