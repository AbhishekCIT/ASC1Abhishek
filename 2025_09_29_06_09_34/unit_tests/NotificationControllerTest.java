package com.airbook.controller;

import com.airbook.model.NotificationPreference;
import com.airbook.model.NotificationLog;
import com.airbook.service.NotificationService;
import com.airbook.service.PreferenceService;
import com.airbook.service.AirlineStatusService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for NotificationController
 */
class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;
    @Mock
    private PreferenceService preferenceService;
    @Mock
    private AirlineStatusService airlineStatusService;

    @InjectMocks
    private NotificationController notificationController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test setPreferences saves preferences and returns confirmation
     */
    @Test
    void testSetPreferences_ValidPreference_ReturnsSaved() {
        NotificationPreference preference = new NotificationPreference();
        doNothing().when(preferenceService).savePreferences(preference);

        ResponseEntity<String> response = notificationController.setPreferences(preference);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("SAVED", response.getBody());
    }

    /**
     * Test getPreferences returns channels for valid user
     */
    @Test
    void testGetPreferences_ValidUser_ReturnsChannels() {
        String userId = "user123";
        List<String> channels = Arrays.asList("EMAIL", "SMS");
        when(preferenceService.getPreferences(userId)).thenReturn(channels);

        ResponseEntity<List<String>> response = notificationController.getPreferences(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(channels, response.getBody());
    }

    /**
     * Test getPreferences with empty userId (edge case)
     */
    @Test
    void testGetPreferences_EmptyUserId_ReturnsEmptyList() {
        String userId = "";
        when(preferenceService.getPreferences(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<String>> response = notificationController.getPreferences(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test receiveFlightStatusUpdate triggers notification and returns confirmation
     */
    @Test
    void testReceiveFlightStatusUpdate_ValidUpdate_ReturnsSent() {
        AirlineStatusService.FlightStatusUpdate update = mock(AirlineStatusService.FlightStatusUpdate.class);
        doNothing().when(notificationService).sendFlightStatusNotification(update);

        ResponseEntity<String> response = notificationController.receiveFlightStatusUpdate(update);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("SENT", response.getBody());
    }

    /**
     * Test getNotificationLogs returns logs for valid user
     */
    @Test
    void testGetNotificationLogs_ValidUser_ReturnsLogs() {
        String userId = "user123";
        List<NotificationLog> logs = Arrays.asList(new NotificationLog(), new NotificationLog());
        when(notificationService.getNotificationLogs(userId)).thenReturn(logs);

        ResponseEntity<List<NotificationLog>> response = notificationController.getNotificationLogs(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(logs, response.getBody());
    }

    /**
     * Test getNotificationLogs with empty userId (edge case)
     */
    @Test
    void testGetNotificationLogs_EmptyUserId_ReturnsEmptyList() {
        String userId = "";
        when(notificationService.getNotificationLogs(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<NotificationLog>> response = notificationController.getNotificationLogs(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }
}
