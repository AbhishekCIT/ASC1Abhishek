package com.example.scheduledreporting.service;

import com.example.scheduledreporting.entity.ScheduledReport;
import com.example.scheduledreporting.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for logging scheduling actions for audit purposes.
 */
@Service
@Slf4j
public class AuditLogService {
    /**
     * Log an action performed on a scheduled report.
     * @param user The user performing the action
     * @param actionType The type of action (CREATE, UPDATE, DELETE, DELIVER)
     * @param report The scheduled report involved
     */
    public void logAction(User user, String actionType, ScheduledReport report) {
        // TODO: Persist audit log to database or external system
        log.info("AUDIT: User {} performed {} on ScheduledReport {} at {}", user.getUsername(), actionType, report.getId(), java.time.LocalDateTime.now());
    }
}
