package com.airtransport.controller;

import com.airtransport.model.FlightSearchRequest;
import com.airtransport.model.FlightSearchResponse;
import com.airtransport.service.FlightSearchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("searchFlights returns OK response for valid request (normal scenario)")
    void testSearchFlights_ValidRequest_ReturnsOk() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("JFK", "LAX", "2025-12-01", 1);
        FlightSearchResponse response = new FlightSearchResponse();
        when(flightSearchService.searchFlights(request)).thenReturn(response);
        // Act
        ResponseEntity<?> entity = controller.searchFlights(request);
        // Assert
        assertEquals(200, entity.getStatusCodeValue(), "Should return 200 OK");
        assertSame(response, entity.getBody(), "Response body should match");
    }

    @Test
    @DisplayName("searchFlights returns BadRequest for service exception (error scenario)")
    void testSearchFlights_ServiceThrowsException_ReturnsBadRequest() {
        // Arrange
        FlightSearchRequest request = new FlightSearchRequest("JFK", "LAX", "2025-12-01", 1);
        when(flightSearchService.searchFlights(request)).thenThrow(new RuntimeException("Service error"));
        // Act
        ResponseEntity<?> entity = controller.searchFlights(request);
        // Assert
        assertEquals(400, entity.getStatusCodeValue(), "Should return 400 Bad Request");
        assertTrue(entity.getBody() instanceof FlightSearchResponse, "Body should be FlightSearchResponse");
        assertEquals("Service error", ((FlightSearchResponse) entity.getBody()).getErrorMessage());
    }

    @Test
    @DisplayName("searchFlights handles null request (edge case)")
    void testSearchFlights_NullRequest() {
        // Act
        ResponseEntity<?> entity = controller.searchFlights(null);
        // Assert
        assertEquals(400, entity.getStatusCodeValue(), "Should return 400 Bad Request for null request");
        assertTrue(entity.getBody() instanceof FlightSearchResponse);
    }
}
