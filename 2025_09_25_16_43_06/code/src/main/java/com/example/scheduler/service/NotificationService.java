package com.example.scheduler.service;

import com.example.scheduler.model.NotificationRequest;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications via email/download link.
 */
@Service
public class NotificationService {
    /**
     * Send notification to recipients (stub implementation).
     */
    public NotificationResponse sendNotification(NotificationRequest request) {
        // Integrate with SMTP/SendGrid here
        NotificationResponse response = new NotificationResponse();
        response.setNotificationId(201L);
        response.setStatus("Sent");
        return response;
    }

    // NotificationResponse inner class for demo
    public static class NotificationResponse {
        private Long notificationId;
        private String status;
        public Long getNotificationId() { return notificationId; }
        public void setNotificationId(Long notificationId) { this.notificationId = notificationId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
