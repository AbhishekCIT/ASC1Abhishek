package com.example.airtransport.controller;

import com.example.airtransport.model.FlightSearchCriteria;
import com.example.airtransport.model.FlightSearchResponse;
import com.example.airtransport.service.FlightSearchService;
import com.example.airtransport.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * JUnit tests for FlightSearchController.
 * Covers normal, edge, boundary, and error-handling scenarios for searchFlights endpoint.
 */
public class FlightSearchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FlightSearchService flightSearchService;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private FlightSearchController flightSearchController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightSearchController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Test normal flight search scenario with results.
     */
    @Test
    void testSearchFlights_Success() throws Exception {
        FlightSearchCriteria criteria = new FlightSearchCriteria();
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(Collections.singletonList(new com.example.airtransport.model.Flight()));
        when(flightSearchService.searchFlights(any(FlightSearchCriteria.class))).thenReturn(response);
        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isOk());
    }

    /**
     * Test flight search scenario with no results (edge case).
     */
    @Test
    void testSearchFlights_NoResults() throws Exception {
        FlightSearchCriteria criteria = new FlightSearchCriteria();
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(Collections.emptyList());
        when(flightSearchService.searchFlights(any(FlightSearchCriteria.class))).thenReturn(response);
        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No flights found for the given criteria."));
    }

    /**
     * Test flight search with invalid input (validation failure).
     */
    @Test
    void testSearchFlights_InvalidInput() throws Exception {
        FlightSearchCriteria criteria = new FlightSearchCriteria();
        doThrow(new IllegalArgumentException("Invalid search criteria")).when(validationUtil).validateSearchCriteria(any(FlightSearchCriteria.class));
        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid search criteria"));
    }

    /**
     * Test flight search with unexpected internal server error.
     */
    @Test
    void testSearchFlights_InternalServerError() throws Exception {
        FlightSearchCriteria criteria = new FlightSearchCriteria();
        doThrow(new RuntimeException("Unexpected error")).when(flightSearchService).searchFlights(any(FlightSearchCriteria.class));
        mockMvc.perform(post("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(criteria)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal server error."));
    }
}
