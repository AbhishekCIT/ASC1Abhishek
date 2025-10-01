package com.example.airlinebooking.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Utility class for sending notifications to users (asynchronous).
 */
@Component
public class NotificationService {
    /**
     * Send booking confirmation notification to user.
     * @param userId User ID
     * @param message Notification message
     */
    @Async
    public void sendConfirmation(String userId, String message) {
        // TODO: Integrate with real notification system (email/SMS/push)
        System.out.println("Notification sent to user " + userId + ": " + message);
    }
}
