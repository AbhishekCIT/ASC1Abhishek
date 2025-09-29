package com.example.airline.controller;

import com.example.airline.dto.NotificationRequest;
import com.example.airline.dto.NotificationResponse;
import com.example.airline.service.NotificationService;
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
 * Unit tests for NotificationController.
 * Covers normal, edge, boundary, and error-handling scenarios for notification API.
 */
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test sending notification with valid input (normal scenario).
     */
    @Test
    @DisplayName("sendNotification: should return SENT status for valid request")
    void sendNotification_validRequest_returnsSentStatus() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId("U123");
        request.setType("EMAIL");
        request.setMessage("Booking Confirmed");

        doNothing().when(notificationService).sendNotification("U123", "EMAIL", "Booking Confirmed");

        ResponseEntity<NotificationResponse> response = notificationController.sendNotification(request);

        assertNotNull(response);
        assertEquals("SENT", response.getBody().getNotificationStatus());
        verify(notificationService, times(1)).sendNotification("U123", "EMAIL", "Booking Confirmed");
    }

    /**
     * Test sending notification with null request (edge case).
     */
    @Test
    @DisplayName("sendNotification: should throw NullPointerException for null request")
    void sendNotification_nullRequest_throwsException() {
        assertThrows(NullPointerException.class, () -> {
            notificationController.sendNotification(null);
        });
    }

    /**
     * Test sending notification with missing userId (boundary/validation case).
     */
    @Test
    @DisplayName("sendNotification: should handle missing userId")
    void sendNotification_missingUserId() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(null);
        request.setType("EMAIL");
        request.setMessage("Test");
        doNothing().when(notificationService).sendNotification(null, "EMAIL", "Test");
        ResponseEntity<NotificationResponse> response = notificationController.sendNotification(request);
        assertNotNull(response);
        assertEquals("SENT", response.getBody().getNotificationStatus());
    }

    /**
     * Test sending notification when NotificationService throws exception (error-handling scenario).
     */
    @Test
    @DisplayName("sendNotification: should propagate exception from NotificationService")
    void sendNotification_serviceThrowsException_propagates() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId("U123");
        request.setType("EMAIL");
        request.setMessage("Booking Confirmed");
        doThrow(new RuntimeException("Notification error")).when(notificationService).sendNotification("U123", "EMAIL", "Booking Confirmed");
        assertThrows(RuntimeException.class, () -> notificationController.sendNotification(request));
    }
}
