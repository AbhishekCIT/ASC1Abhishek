package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.util.GDSClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {
    @Mock
    private GDSClient gdsClient;
    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("searchFlights - success scenario")
    void testSearchFlightsSuccess() {
        Flight flight = new Flight(123L, "JFK", "LAX", LocalDateTime.of(2025, 10, 1, 9, 0), 350.00);
        List<Flight> flights = Collections.singletonList(flight);
        when(gdsClient.fetchFlights(eq("JFK"), eq("LAX"), eq(LocalDate.of(2025, 10, 1)))).thenReturn(flights);
        List<Flight> result = flightService.searchFlights("JFK", "LAX", "2025-10-01");
        assertEquals(1, result.size());
        assertEquals("JFK", result.get(0).getOrigin());
        assertEquals("LAX", result.get(0).getDestination());
    }

    @Test
    @DisplayName("searchFlights - invalid origin airport code")
    void testSearchFlightsInvalidOrigin() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            flightService.searchFlights("J1K", "LAX", "2025-10-01")
        );
        assertEquals("Invalid origin airport code", ex.getMessage());
    }

    @Test
    @DisplayName("searchFlights - invalid destination airport code")
    void testSearchFlightsInvalidDestination() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            flightService.searchFlights("JFK", "LA1", "2025-10-01")
        );
        assertEquals("Invalid destination airport code", ex.getMessage());
    }

    @Test
    @DisplayName("searchFlights - travel date in the past")
    void testSearchFlightsPastDate() {
        String pastDate = LocalDate.now().minusDays(1).toString();
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            flightService.searchFlights("JFK", "LAX", pastDate)
        );
        assertEquals("Travel date cannot be in the past", ex.getMessage());
    }

    @Test
    @DisplayName("getSeats - success scenario")
    void testGetSeatsSuccess() {
        Seat seat1 = new Seat(1L, 123L, "12A", true);
        Seat seat2 = new Seat(2L, 123L, "12B", false);
        List<Seat> seats = Arrays.asList(seat1, seat2);
        when(gdsClient.fetchSeats(123L)).thenReturn(seats);
        List<Seat> result = flightService.getSeats(123L);
        assertEquals(2, result.size());
        assertEquals("12A", result.get(0).getSeatNo());
        assertFalse(result.get(1).getAvailable());
    }

    @Test
    @DisplayName("getSeats - empty seat list")
    void testGetSeatsEmpty() {
        when(gdsClient.fetchSeats(999L)).thenReturn(Collections.emptyList());
        List<Seat> result = flightService.getSeats(999L);
        assertTrue(result.isEmpty());
    }
}
