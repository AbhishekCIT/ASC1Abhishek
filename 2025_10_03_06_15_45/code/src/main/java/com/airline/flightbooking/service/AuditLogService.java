package com.airline.flightbooking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for logging audit events.
 */
@Service
@Slf4j
public class AuditLogService {
    /**
     * Log an event with type and details.
     * @param eventType type of event (e.g., BOOKING_CREATED)
     * @param details event details
     */
    public void logEvent(String eventType, String details) {
        // In real implementation, send to external audit logging system
        log.info("[AUDIT] {} - {}", eventType, details);
    }
}
