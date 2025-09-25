package com.example.scheduler.service;

import com.example.scheduler.model.NotificationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationService.
 */
class NotificationServiceTest {
    private final NotificationService notificationService = new NotificationService();

    @Test
    @DisplayName("Should send notification and return response with status 'Sent'")
    void testSendNotification_Success() {
        NotificationRequest request = new NotificationRequest();
        request.setScheduleId(1L);
        request.setStatus("Delivered");
        request.setRecipients(Arrays.asList("user@example.com"));
        NotificationService.NotificationResponse response = notificationService.sendNotification(request);
        assertNotNull(response);
        assertEquals(201L, response.getNotificationId());
        assertEquals("Sent", response.getStatus());
    }

    @Test
    @DisplayName("Should handle null request gracefully (edge case)")
    void testSendNotification_NullRequest() {
        NotificationService.NotificationResponse response = notificationService.sendNotification(null);
        assertNotNull(response);
        assertEquals(201L, response.getNotificationId());
        assertEquals("Sent", response.getStatus());
    }
}
