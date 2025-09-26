package com.airtransport.controller;

import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.model.ValidationRequest;
import com.airtransport.model.ValidationResponse;
import com.airtransport.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FlightSearchController.
 */
public class FlightSearchControllerTest {
    @Mock
    private FlightSearchService flightSearchService;

    @InjectMocks
    private FlightSearchController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal scenario: searchFlights returns OK response with flights.
     */
    @Test
    void testSearchFlights_NormalScenario() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(Collections.emptyList());
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        ResponseEntity<FlightSearchResponse> entity = controller.searchFlights(request);
        assertEquals(200, entity.getStatusCodeValue());
        assertNotNull(entity.getBody());
    }

    /**
     * Test edge case: searchFlights returns empty flights list.
     */
    @Test
    void testSearchFlights_EmptyFlights() {
        FlightSearchRequest request = new FlightSearchRequest();
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(Collections.emptyList());
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        ResponseEntity<FlightSearchResponse> entity = controller.searchFlights(request);
        assertTrue(entity.getBody().getFlights().isEmpty());
    }

    /**
     * Test error scenario: searchFlights throws exception.
     */
    @Test
    void testSearchFlights_Exception() {
        FlightSearchRequest request = new FlightSearchRequest();
        when(flightSearchService.searchFlights(request)).thenThrow(new RuntimeException("Service error"));
        assertThrows(RuntimeException.class, () -> controller.searchFlights(request));
    }

    /**
     * Test normal scenario: validateInputs returns OK response with valid result.
     */
    @Test
    void testValidateInputs_NormalScenario() {
        ValidationRequest request = new ValidationRequest();
        ValidationResponse response = new ValidationResponse(true, Collections.emptyList());
        when(flightSearchService.validateInputs(request)).thenReturn(response);
        ResponseEntity<ValidationResponse> entity = controller.validateInputs(request);
        assertEquals(200, entity.getStatusCodeValue());
        assertTrue(entity.getBody().isValid());
    }

    /**
     * Test edge case: validateInputs returns invalid result with errors.
     */
    @Test
    void testValidateInputs_Invalid() {
        ValidationRequest request = new ValidationRequest();
        ValidationResponse response = new ValidationResponse(false, Collections.singletonList("Invalid input"));
        when(flightSearchService.validateInputs(request)).thenReturn(response);
        ResponseEntity<ValidationResponse> entity = controller.validateInputs(request);
        assertFalse(entity.getBody().isValid());
        assertFalse(entity.getBody().getErrors().isEmpty());
    }
}
