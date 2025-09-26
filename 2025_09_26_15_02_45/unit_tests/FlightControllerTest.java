package com.example.airbooking.controller;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
class FlightControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Search flights - success scenario")
    void testSearchFlightsSuccess() throws Exception {
        // Arrange
        Flight flight = new Flight(123L, "JFK", "LAX", LocalDateTime.of(2025, 10, 1, 9, 0), 350.00);
        List<Flight> flights = Collections.singletonList(flight);
        when(flightService.searchFlights(eq("JFK"), eq("LAX"), eq("2025-10-01"))).thenReturn(flights);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2025-10-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flightId").value(123L))
                .andExpect(jsonPath("$[0].origin").value("JFK"))
                .andExpect(jsonPath("$[0].destination").value("LAX"));
    }

    @Test
    @DisplayName("Search flights - invalid airport code")
    void testSearchFlightsInvalidAirportCode() throws Exception {
        // Arrange
        when(flightService.searchFlights(eq("J1K"), eq("LAX"), eq("2025-10-01"))).thenThrow(new IllegalArgumentException("Invalid origin airport code"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights/search")
                .param("origin", "J1K")
                .param("destination", "LAX")
                .param("date", "2025-10-01"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Search flights - travel date in the past")
    void testSearchFlightsPastDate() throws Exception {
        // Arrange
        when(flightService.searchFlights(eq("JFK"), eq("LAX"), eq("2000-01-01"))).thenThrow(new IllegalArgumentException("Travel date cannot be in the past"));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2000-01-01"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Get seats - success scenario")
    void testGetSeatsSuccess() throws Exception {
        // Arrange
        Seat seat1 = new Seat(1L, 123L, "12A", true);
        Seat seat2 = new Seat(2L, 123L, "12B", false);
        List<Seat> seats = Arrays.asList(seat1, seat2);
        when(flightService.getSeats(eq(123L))).thenReturn(seats);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights/123/seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seatNo").value("12A"))
                .andExpect(jsonPath("$[0].available").value(true))
                .andExpect(jsonPath("$[1].seatNo").value("12B"))
                .andExpect(jsonPath("$[1].available").value(false));
    }

    @Test
    @DisplayName("Get seats - flight not found (empty list)")
    void testGetSeatsFlightNotFound() throws Exception {
        // Arrange
        when(flightService.getSeats(eq(999L))).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/flights/999/seats"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
