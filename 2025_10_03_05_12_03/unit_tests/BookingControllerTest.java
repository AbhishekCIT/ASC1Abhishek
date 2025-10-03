package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
import com.example.airtransport.util.ValidationUtil;
import com.example.airtransport.exception.PaymentFailedException;
import com.example.airtransport.exception.SeatUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JUnit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for createBooking endpoint.
 */
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private BookingController bookingController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Test normal booking scenario.
     */
    @Test
    void testCreateBooking_Success() throws Exception {
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();
        when(bookingService.bookFlight(any(BookingRequest.class))).thenReturn(response);
        // No exception from validationUtil
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test booking with invalid request (validation failure).
     */
    @Test
    void testCreateBooking_InvalidRequest() throws Exception {
        BookingRequest request = new BookingRequest();
        doThrow(new IllegalArgumentException("Invalid request")).when(validationUtil).validateBookingRequest(any(BookingRequest.class));
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid request"));
    }

    /**
     * Test booking with payment failure.
     */
    @Test
    void testCreateBooking_PaymentFailed() throws Exception {
        BookingRequest request = new BookingRequest();
        doThrow(new PaymentFailedException("Payment failed")).when(bookingService).bookFlight(any(BookingRequest.class));
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isPaymentRequired())
                .andExpect(content().string("Payment failed"));
    }

    /**
     * Test booking with seat unavailable scenario.
     */
    @Test
    void testCreateBooking_SeatUnavailable() throws Exception {
        BookingRequest request = new BookingRequest();
        doThrow(new SeatUnavailableException("Seat unavailable")).when(bookingService).bookFlight(any(BookingRequest.class));
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Seat unavailable"));
    }

    /**
     * Test booking with unexpected internal server error.
     */
    @Test
    void testCreateBooking_InternalServerError() throws Exception {
        BookingRequest request = new BookingRequest();
        doThrow(new RuntimeException("Unexpected error")).when(bookingService).bookFlight(any(BookingRequest.class));
        mockMvc.perform(post("/api/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error."));
    }
}
