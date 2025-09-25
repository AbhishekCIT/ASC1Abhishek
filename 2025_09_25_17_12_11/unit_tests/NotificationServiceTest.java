package com.example.scheduler.service;

import com.example.scheduler.model.NotificationRequest;
import com.example.scheduler.model.NotificationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationService.
 */
class NotificationServiceTest {
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    @DisplayName("Send notification - success")
    void testSendNotificationSuccess() {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(123L);
        request.setMessage("Test notification");
        NotificationResponse response = notificationService.sendNotification(request);
        assertNotNull(response);
        assertEquals("NOTIFIED", response.getStatus());
    }

    @Test
    @DisplayName("Send confirmation - no exception")
    void testSendConfirmationNoException() {
        // This method just logs, so we check that no exception is thrown
        assertDoesNotThrow(() -> notificationService.sendConfirmation(101L, "CREATED"));
        assertDoesNotThrow(() -> notificationService.sendConfirmation(102L, "UPDATED"));
        assertDoesNotThrow(() -> notificationService.sendConfirmation(103L, "DELETED"));
    }
}
