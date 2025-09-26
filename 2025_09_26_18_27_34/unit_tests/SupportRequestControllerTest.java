package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.SupportRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SupportRequestController.
 */
class SupportRequestControllerTest {

    @Mock
    private SupportRequestService supportRequestService;

    @InjectMocks
    private SupportRequestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test createSupportRequest - normal scenario")
    void testCreateSupportRequest_Success() {
        // Arrange
        SupportRequestInput request = new SupportRequestInput();
        SupportRequestResponse responseObj = new SupportRequestResponse();
        when(supportRequestService.createRequest(request, "Bearer token")).thenReturn(responseObj);

        // Act
        ResponseEntity<?> response = controller.createSupportRequest(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(responseObj, response.getBody());
    }

    @Test
    @DisplayName("Test createSupportRequest - error scenario")
    void testCreateSupportRequest_Error() {
        // Arrange
        SupportRequestInput request = new SupportRequestInput();
        when(supportRequestService.createRequest(request, "Bearer token"))
                .thenThrow(new IllegalArgumentException("Invalid request"));

        // Act
        ResponseEntity<?> response = controller.createSupportRequest(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody());
    }

    @Test
    @DisplayName("Test getSupportRequestStatus - normal scenario")
    void testGetSupportRequestStatus_Success() {
        // Arrange
        SupportRequestStatusResponse statusResponse = new SupportRequestStatusResponse();
        when(supportRequestService.getRequestStatus("REQ123", "Bearer token")).thenReturn(statusResponse);

        // Act
        ResponseEntity<?> response = controller.getSupportRequestStatus("REQ123", "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(statusResponse, response.getBody());
    }

    @Test
    @DisplayName("Test getSupportRequestStatus - not found scenario")
    void testGetSupportRequestStatus_NotFound() {
        // Arrange
        when(supportRequestService.getRequestStatus("REQ404", "Bearer token"))
                .thenThrow(new RuntimeException("Request not found"));

        // Act
        ResponseEntity<?> response = controller.getSupportRequestStatus("REQ404", "Bearer token");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Request not found", response.getBody());
    }

    @Test
    @DisplayName("Test escalateSupportRequest - normal scenario")
    void testEscalateSupportRequest_Success() {
        // Arrange
        SupportRequestEscalationResponse escalationResponse = new SupportRequestEscalationResponse();
        when(supportRequestService.escalateRequest("REQ123", "Bearer token")).thenReturn(escalationResponse);

        // Act
        ResponseEntity<?> response = controller.escalateSupportRequest("REQ123", "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(escalationResponse, response.getBody());
    }

    @Test
    @DisplayName("Test escalateSupportRequest - error scenario")
    void testEscalateSupportRequest_Error() {
        // Arrange
        when(supportRequestService.escalateRequest("REQ500", "Bearer token"))
                .thenThrow(new RuntimeException("Escalation failed"));

        // Act
        ResponseEntity<?> response = controller.escalateSupportRequest("REQ500", "Bearer token");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Escalation failed", response.getBody());
    }
}
