package com.example.scheduler.service;

import com.example.scheduler.model.NotificationRequest;
import com.example.scheduler.model.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for sending notifications to users.
 */
@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    /**
     * Send notification to user.
     */
    public NotificationResponse sendNotification(NotificationRequest request) {
        // Simulate sending notification (email, in-app, etc.)
        logger.info("Notification sent to user {}: {}", request.getUserId(), request.getMessage());
        return new NotificationResponse("NOTIFIED");
    }

    /**
     * Send confirmation for schedule actions.
     */
    public void sendConfirmation(Long scheduleId, String status) {
        // Simulate sending confirmation notification
        logger.info("Confirmation sent for schedule {}: {}", scheduleId, status);
    }
}
