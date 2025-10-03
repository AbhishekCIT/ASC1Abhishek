package com.example.airtransport.controller;

import com.example.airtransport.model.CancellationRequest;
import com.example.airtransport.model.CancellationResponse;
import com.example.airtransport.service.CancellationService;
import com.example.airtransport.util.ValidationUtil;
import com.example.airtransport.exception.IneligibleBookingException;
import com.example.airtransport.exception.RefundCalculationException;
import com.example.airtransport.exception.RefundFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit tests for CancellationController.
 * Covers normal, edge, boundary, and error-handling scenarios for cancelBooking endpoint.
 */
public class CancellationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CancellationService cancellationService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private CancellationController cancellationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cancellationController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Test normal cancellation scenario.
     */
    @Test
    void testCancelBooking_Success() throws Exception {
        CancellationRequest request = new CancellationRequest();
        CancellationResponse response = new CancellationResponse();
        when(cancellationService.cancelBooking(any(CancellationRequest.class))).thenReturn(response);
        // No exception from validationUtil
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test cancellation with invalid request (validation failure).
     */
    @Test
    void testCancelBooking_InvalidRequest() throws Exception {
        CancellationRequest request = new CancellationRequest();
        doThrow(new IllegalArgumentException("Invalid cancellation request")).when(validationUtil).validateCancellationRequest(any(CancellationRequest.class));
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid cancellation request"));
    }

    /**
     * Test cancellation with ineligible booking.
     */
    @Test
    void testCancelBooking_IneligibleBooking() throws Exception {
        CancellationRequest request = new CancellationRequest();
        doThrow(new IneligibleBookingException("Booking not eligible for cancellation")).when(cancellationService).cancelBooking(any(CancellationRequest.class));
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Booking not eligible for cancellation"));
    }

    /**
     * Test cancellation with refund calculation error.
     */
    @Test
    void testCancelBooking_RefundCalculationError() throws Exception {
        CancellationRequest request = new CancellationRequest();
        doThrow(new RefundCalculationException("Refund calculation error")).when(cancellationService).cancelBooking(any(CancellationRequest.class));
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Refund calculation error"));
    }

    /**
     * Test cancellation with refund failed scenario.
     */
    @Test
    void testCancelBooking_RefundFailed() throws Exception {
        CancellationRequest request = new CancellationRequest();
        doThrow(new RefundFailedException("Refund failed")).when(cancellationService).cancelBooking(any(CancellationRequest.class));
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadGateway())
                .andExpect(content().string("Refund failed"));
    }

    /**
     * Test cancellation with unexpected internal server error.
     */
    @Test
    void testCancelBooking_InternalServerError() throws Exception {
        CancellationRequest request = new CancellationRequest();
        doThrow(new RuntimeException("Unexpected error")).when(cancellationService).cancelBooking(any(CancellationRequest.class));
        mockMvc.perform(post("/api/bookings/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error."));
    }
}
