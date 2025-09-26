package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.BookingService;
import com.example.flightbooking.service.FlightService;
import com.example.flightbooking.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for FlightController.
 */
class FlightControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightService flightService;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private FlightController flightController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
        objectMapper = new ObjectMapper();
    }

    // --- searchFlights endpoint tests ---

    @Test
    @DisplayName("searchFlights: should return list of flights for valid request")
    void searchFlights_validRequest_returnsFlights() throws Exception {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        List<Flight> flights = Arrays.asList(
                new Flight("F1", "AA", "JFK", "LAX", LocalDate.now().plusDays(1).atTime(10, 0), 5, 300.0)
        );
        when(flightService.searchFlights(anyString(), anyString(), any(LocalDate.class))).thenReturn(flights);

        // Act & Assert
        mockMvc.perform(get("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].flightId", is("F1")));
    }

    @Test
    @DisplayName("searchFlights: should return bad request for invalid input (empty origin)")
    void searchFlights_invalidOrigin_returnsBadRequest() throws Exception {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        // ValidationUtil will throw IllegalArgumentException
        doThrow(new IllegalArgumentException("Origin cannot be empty"))
                .when(flightService).searchFlights(anyString(), anyString(), any(LocalDate.class));

        // Act & Assert
        mockMvc.perform(get("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Origin cannot be empty")));
    }

    @Test
    @DisplayName("searchFlights: should return bad request for invalid input (past date)")
    void searchFlights_pastDate_returnsBadRequest() throws Exception {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().minusDays(1));
        doThrow(new IllegalArgumentException("Travel date must be in the future"))
                .when(flightService).searchFlights(anyString(), anyString(), any(LocalDate.class));

        // Act & Assert
        mockMvc.perform(get("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Travel date must be in the future")));
    }

    @Test
    @DisplayName("searchFlights: should handle internal server error")
    void searchFlights_internalError_returnsServerError() throws Exception {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate(LocalDate.now().plusDays(1));
        when(flightService.searchFlights(anyString(), anyString(), any(LocalDate.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        mockMvc.perform(get("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Internal server error")));
    }

    // --- bookFlight endpoint tests ---

    @Test
    @DisplayName("bookFlight: should return confirmation for valid booking")
    void bookFlight_validRequest_returnsConfirmation() throws Exception {
        // Arrange
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setFlightId("F1");
        bookingRequest.setUserId("U1");
        bookingRequest.setPaymentInfo(new PaymentInfo());
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId("B1");
        confirmation.setStatus("CONFIRMED");
        confirmation.setPnr("PNR123");
        when(bookingService.bookFlight(any(BookingRequest.class))).thenReturn(confirmation);

        // Act & Assert
        mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId", is("B1")))
                .andExpect(jsonPath("$.status", is("CONFIRMED")))
                .andExpect(jsonPath("$.pnr", is("PNR123")));
    }

    @Test
    @DisplayName("bookFlight: should return bad request for invalid booking (no seats)")
    void bookFlight_noSeats_returnsBadRequest() throws Exception {
        // Arrange
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setFlightId("F1");
        bookingRequest.setUserId("U1");
        bookingRequest.setPaymentInfo(new PaymentInfo());
        when(bookingService.bookFlight(any(BookingRequest.class)))
                .thenThrow(new IllegalArgumentException("No seats available"));

        // Act & Assert
        mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("No seats available")));
    }

    @Test
    @DisplayName("bookFlight: should return bad request for invalid booking (payment failed)")
    void bookFlight_paymentFailed_returnsBadRequest() throws Exception {
        // Arrange
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setFlightId("F1");
        bookingRequest.setUserId("U1");
        bookingRequest.setPaymentInfo(new PaymentInfo());
        when(bookingService.bookFlight(any(BookingRequest.class)))
                .thenThrow(new IllegalArgumentException("Payment authorization failed"));

        // Act & Assert
        mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Payment authorization failed")));
    }

    @Test
    @DisplayName("bookFlight: should handle internal server error")
    void bookFlight_internalError_returnsServerError() throws Exception {
        // Arrange
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setFlightId("F1");
        bookingRequest.setUserId("U1");
        bookingRequest.setPaymentInfo(new PaymentInfo());
        when(bookingService.bookFlight(any(BookingRequest.class)))
                .thenThrow(new RuntimeException("Unexpected error"));

        // Act & Assert
        mockMvc.perform(post("/api/flights/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Internal server error")));
    }
}
