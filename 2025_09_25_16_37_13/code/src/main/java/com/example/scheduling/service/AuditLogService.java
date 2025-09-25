package com.example.scheduling.service;

import com.example.scheduling.model.SchedulingRule;
import org.springframework.stereotype.Service;

/**
 * Stub service for audit logging of rule changes and job executions.
 * In a real implementation, this would integrate with Azure Monitor or a logging system.
 */
@Service
public class AuditLogService {
    public void logChange(String action, SchedulingRule rule, String username) {
        // TODO: Integrate with audit logging system
    }
}
