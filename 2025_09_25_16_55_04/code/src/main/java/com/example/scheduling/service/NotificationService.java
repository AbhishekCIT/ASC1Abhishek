package com.example.scheduling.service;

import com.example.scheduling.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

/**
 * Service class for sending notifications/emails.
 * Stub implementation for integration.
 */
@Service
public class NotificationService {
    /**
     * Sends a confirmation email to recipients after schedule creation or update.
     */
    public void sendConfirmationEmail(ScheduleEntity schedule) {
        // Integrate with SendGrid or other email provider
        // For now, stub implementation
    }
}
