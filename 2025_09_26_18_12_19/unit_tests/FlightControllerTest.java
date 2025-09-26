package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.FlightService;
import com.example.flightbooking.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for FlightController REST endpoints.
 */
@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Test searchFlights returns available flights for valid input")
    void testSearchFlights_Success() throws Exception {
        // Arrange
        Flight flight = new Flight("F123", "JFK", "LAX", "2024-07-10", 350.0, "Economy", 5);
        FlightSearchResponse response = new FlightSearchResponse(Collections.singletonList(flight));
        Mockito.when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(response);

        // Act
        ResultActions result = mockMvc.perform(get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2024-07-10")
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.flights", hasSize(1)))
              .andExpect(jsonPath("$.flights[0].flightId", is("F123")));
    }

    @Test
    @DisplayName("Test searchFlights returns empty list when no flights available")
    void testSearchFlights_NoFlights() throws Exception {
        // Arrange
        FlightSearchResponse response = new FlightSearchResponse(Collections.emptyList());
        Mockito.when(flightService.searchFlights(anyString(), anyString(), anyString())).thenReturn(response);

        // Act
        ResultActions result = mockMvc.perform(get("/api/flights/search")
                .param("origin", "JFK")
                .param("destination", "LAX")
                .param("date", "2024-07-10")
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.flights", hasSize(0)));
    }

    @Test
    @DisplayName("Test bookFlight returns booking confirmation for valid request")
    void testBookFlight_Success() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse("B789", "CONFIRMED", "sent");
        Mockito.when(flightService.bookFlight(any(BookingRequest.class))).thenReturn(response);

        // Act
        ResultActions result = mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.bookingId", is("B789")))
              .andExpect(jsonPath("$.status", is("CONFIRMED")))
              .andExpect(jsonPath("$.confirmationEmail", is("sent")));
    }

    @Test
    @DisplayName("Test bookFlight returns error for invalid booking request")
    void testBookFlight_InvalidRequest() throws Exception {
        // Arrange
        Mockito.when(flightService.bookFlight(any(BookingRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid booking details."));
        BookingRequest request = new BookingRequest();

        // Act
        ResultActions result = mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isInternalServerError()); // Or customize if controller advice is present
    }
}
