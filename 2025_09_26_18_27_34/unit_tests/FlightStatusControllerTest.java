package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.FlightStatusService;
import com.example.flightbooking.service.NotificationService;
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
 * Unit tests for FlightStatusController.
 */
class FlightStatusControllerTest {

    @Mock
    private FlightStatusService flightStatusService;
    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private FlightStatusController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test getFlightStatus - by flight number")
    void testGetFlightStatus_ByFlightNumber() {
        // Arrange
        FlightStatus status = new FlightStatus();
        when(flightStatusService.getStatus(eq("FL123"), isNull())).thenReturn(status);

        // Act
        ResponseEntity<?> response = controller.getFlightStatus("FL123", null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(status, response.getBody());
    }

    @Test
    @DisplayName("Test getFlightStatus - by booking reference")
    void testGetFlightStatus_ByBookingReference() {
        // Arrange
        FlightStatus status = new FlightStatus();
        when(flightStatusService.getStatus(isNull(), eq("BR456"))).thenReturn(status);

        // Act
        ResponseEntity<?> response = controller.getFlightStatus(null, "BR456");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(status, response.getBody());
    }

    @Test
    @DisplayName("Test getFlightStatus - error scenario")
    void testGetFlightStatus_Error() {
        // Arrange
        when(flightStatusService.getStatus(eq("BAD"), isNull())).thenThrow(new IllegalArgumentException("Invalid flight number"));

        // Act
        ResponseEntity<?> response = controller.getFlightStatus("BAD", null);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid flight number", response.getBody());
    }

    @Test
    @DisplayName("Test subscribeToNotifications - normal scenario")
    void testSubscribeToNotifications_Success() {
        // Arrange
        NotificationSubscriptionRequest request = new NotificationSubscriptionRequest();
        NotificationSubscription subscription = new NotificationSubscription();
        when(notificationService.subscribe(request, "Bearer token")).thenReturn(subscription);

        // Act
        ResponseEntity<?> response = controller.subscribeToNotifications(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(subscription, response.getBody());
    }

    @Test
    @DisplayName("Test subscribeToNotifications - error scenario")
    void testSubscribeToNotifications_Error() {
        // Arrange
        NotificationSubscriptionRequest request = new NotificationSubscriptionRequest();
        when(notificationService.subscribe(request, "Bearer token"))
                .thenThrow(new RuntimeException("Subscription failed"));

        // Act
        ResponseEntity<?> response = controller.subscribeToNotifications(request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Subscription failed", response.getBody());
    }
}
