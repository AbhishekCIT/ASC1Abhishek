package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.BookingService;
import com.example.airbooking.service.FlightSearchService;
import com.example.airbooking.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for UserController.
 * Purpose: Test all user-facing endpoints for normal, edge, boundary, and error scenarios.
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightSearchService flightSearchService;
    @MockBean
    private BookingService bookingService;
    @MockBean
    private PaymentService paymentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test: Search flights - normal scenario
     */
    @Test
    void testSearchFlights_Normal() throws Exception {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        Mockito.when(flightSearchService.searchFlights(any())).thenReturn(List.of(response));
        mockMvc.perform(get("/api/flights/search")
                .param("origin", "NYC")
                .param("destination", "LAX")
                .param("date", "2025-12-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    /**
     * Test: Search flights - edge case (empty result)
     */
    @Test
    void testSearchFlights_EmptyResult() throws Exception {
        Mockito.when(flightSearchService.searchFlights(any())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/flights/search")
                .param("origin", "NYC")
                .param("destination", "LAX")
                .param("date", "2025-12-01"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    /**
     * Test: Book flight - normal scenario
     */
    @Test
    void testBookFlight_Normal() throws Exception {
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();
        Mockito.when(bookingService.createBooking(any())).thenReturn(response);
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test: Book flight - error scenario (invalid input)
     */
    @Test
    void testBookFlight_InvalidInput() throws Exception {
        BookingRequest request = new BookingRequest();
        // Simulate invalid input by omitting required fields
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test: Make payment - normal scenario
     */
    @Test
    void testPay_Normal() throws Exception {
        PaymentRequest request = new PaymentRequest();
        PaymentResponse response = new PaymentResponse();
        Mockito.when(paymentService.processPayment(any())).thenReturn(response);
        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test: Make payment - error scenario (invalid input)
     */
    @Test
    void testPay_InvalidInput() throws Exception {
        PaymentRequest request = new PaymentRequest();
        // Simulate invalid input by omitting required fields
        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test: Confirm booking - normal scenario
     */
    @Test
    void testConfirmBooking_Normal() throws Exception {
        BookingConfirmResponse response = new BookingConfirmResponse();
        Mockito.when(bookingService.confirmBooking(eq(1))).thenReturn(response);
        mockMvc.perform(get("/api/bookings/1/confirm"))
                .andExpect(status().isOk());
    }

    /**
     * Test: Confirm booking - error scenario (not found)
     */
    @Test
    void testConfirmBooking_NotFound() throws Exception {
        Mockito.when(bookingService.confirmBooking(eq(999))).thenThrow(new RuntimeException("Booking not found"));
        mockMvc.perform(get("/api/bookings/999/confirm"))
                .andExpect(status().isInternalServerError());
    }
}
