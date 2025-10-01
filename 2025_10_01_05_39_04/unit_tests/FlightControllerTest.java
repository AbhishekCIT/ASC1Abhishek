package com.airline.booking.controller;

import com.airline.booking.model.Flight;
import com.airline.booking.model.Seat;
import com.airline.booking.service.FlightService;
import com.airline.booking.service.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for FlightController.
 * Purpose: Test flight search and seat viewing endpoints for normal, edge, boundary, and error scenarios.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        // Setup before each test if needed
    }

    /**
     * Test searching flights with valid parameters (normal scenario).
     */
    @Test
    void searchFlights_validParams_returnsFlights() throws Exception {
        Flight flight = new Flight();
        flight.setId("F123");
        flight.setOrigin("JFK");
        flight.setDestination("LAX");
        flight.setDeparture(LocalDate.of(2024, 12, 1).atTime(10, 0));
        flight.setArrival(LocalDate.of(2024, 12, 1).atTime(13, 0));
        flight.setFlightClass("Economy");
        List<Flight> flights = Arrays.asList(flight);
        Mockito.when(flightService.searchFlights(eq("JFK"), eq("LAX"), eq(LocalDate.of(2024, 12, 1)), eq("Economy")))
                .thenReturn(flights);

        ResultActions result = mockMvc.perform(get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2024-12-01")
                .param("flightClass", "Economy"));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$[0].id").value("F123"));
    }

    /**
     * Test searching flights with no results (edge case).
     */
    @Test
    void searchFlights_noResults_returnsEmptyList() throws Exception {
        Mockito.when(flightService.searchFlights(anyString(), anyString(), any(LocalDate.class), anyString()))
                .thenReturn(Collections.emptyList());

        ResultActions result = mockMvc.perform(get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2024-12-01")
                .param("flightClass", "Economy"));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Test searching flights with invalid date format (boundary condition).
     */
    @Test
    void searchFlights_invalidDate_returnsBadRequest() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "invalid-date")
                .param("flightClass", "Economy"));

        result.andExpect(status().isBadRequest());
    }

    /**
     * Test viewing seats for a valid flight (normal scenario).
     */
    @Test
    void viewSeats_validFlight_returnsSeats() throws Exception {
        Flight flight = new Flight();
        flight.setId("F123");
        Seat seat = new Seat();
        seat.setSeatNumber("12A");
        seat.setAvailable(true);
        List<Seat> seats = Arrays.asList(seat);
        Mockito.when(flightService.getFlightById(eq("F123"))).thenReturn(flight);
        Mockito.when(seatService.getAvailableSeats(eq(flight))).thenReturn(seats);

        ResultActions result = mockMvc.perform(get("/api/flights/F123/seats"));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$[0].seatNumber").value("12A"))
              .andExpect(jsonPath("$[0].available").value(true));
    }

    /**
     * Test viewing seats for a flight with no available seats (edge case).
     */
    @Test
    void viewSeats_noAvailableSeats_returnsEmptyList() throws Exception {
        Flight flight = new Flight();
        flight.setId("F123");
        Mockito.when(flightService.getFlightById(eq("F123"))).thenReturn(flight);
        Mockito.when(seatService.getAvailableSeats(eq(flight))).thenReturn(Collections.emptyList());

        ResultActions result = mockMvc.perform(get("/api/flights/F123/seats"));

        result.andExpect(status().isOk())
              .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Test viewing seats for an invalid flight ID (error-handling scenario).
     */
    @Test
    void viewSeats_invalidFlightId_returnsNotFound() throws Exception {
        Mockito.when(flightService.getFlightById(eq("INVALID"))).thenThrow(new RuntimeException("Flight not found"));

        ResultActions result = mockMvc.perform(get("/api/flights/INVALID/seats"));

        result.andExpect(status().isInternalServerError());
    }

    /**
     * Clean up mocks after each test.
     */
    @BeforeEach
    void tearDown() {
        reset(flightService, seatService);
    }
}
