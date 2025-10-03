package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightInventoryService.
 */
public class FlightInventoryServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightInventoryService flightInventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test getAvailableFlights normal scenario.
     */
    @Test
    void testGetAvailableFlights() {
        when(flightRepository.findAvailableFlights("JFK", "LAX", "2025-10-10", 2))
                .thenReturn(Arrays.asList(new Flight(), new Flight()));
        assertEquals(2, flightInventoryService.getAvailableFlights("JFK", "LAX", "2025-10-10", 2).size());
    }

    /**
     * Test getFlightById returns flight.
     */
    @Test
    void testGetFlightById_Found() {
        Flight flight = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        assertEquals(flight, flightInventoryService.getFlightById(1L));
    }

    /**
     * Test getFlightById returns null if not found.
     */
    @Test
    void testGetFlightById_NotFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(flightInventoryService.getFlightById(1L));
    }

    /**
     * Test reserveSeats normal scenario.
     */
    @Test
    void testReserveSeats_Success() {
        Flight flight = new Flight();
        flight.setSeatsAvailable(10);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        assertTrue(flightInventoryService.reserveSeats(1L, 5));
        assertEquals(5, flight.getSeatsAvailable());
    }

    /**
     * Test reserveSeats with insufficient seats (edge case).
     */
    @Test
    void testReserveSeats_InsufficientSeats() {
        Flight flight = new Flight();
        flight.setSeatsAvailable(2);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        assertFalse(flightInventoryService.reserveSeats(1L, 5));
    }

    /**
     * Test reserveSeats with null flight (error case).
     */
    @Test
    void testReserveSeats_NullFlight() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());
        assertFalse(flightInventoryService.reserveSeats(1L, 1));
    }
}
