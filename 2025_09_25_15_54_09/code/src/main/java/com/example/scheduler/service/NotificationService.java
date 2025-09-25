package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import org.springframework.stereotype.Service;

/**
 * Service for user notifications (success/failure).
 */
@Service
public class NotificationService {
    /**
     * Notify user of report delivery status.
     * @param schedule The schedule
     * @param success true if delivery succeeded
     * @param message Optional error message
     */
    public void notifyUser(Schedule schedule, boolean success, String message) {
        // Placeholder: Implement actual notification logic (e.g., email, in-app)
    }
}
