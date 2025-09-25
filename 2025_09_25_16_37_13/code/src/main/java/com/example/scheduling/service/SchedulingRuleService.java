package com.example.scheduling.service;

import com.example.scheduling.model.SchedulingRule;
import com.example.scheduling.model.ReportType;
import com.example.scheduling.repository.SchedulingRuleRepository;
import com.example.scheduling.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Service for business logic and validations related to SchedulingRule.
 */
@Service
public class SchedulingRuleService {
    private static final List<String> ALLOWED_FREQUENCIES = List.of("daily", "weekly", "monthly");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    private SchedulingRuleRepository schedulingRuleRepository;
    @Autowired
    private ReportTypeService reportTypeService;
    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private AuditLogService auditLogService;

    /**
     * Create a new scheduling rule after validation.
     */
    @Transactional
    public SchedulingRule createRule(Long reportTypeId, String frequency, String time, boolean isActive, String username) {
        validateFrequency(frequency);
        validateTimeFormat(time);
        ReportType reportType = reportTypeService.getReportTypeById(reportTypeId)
                .orElseThrow(() -> new ReportTypeNotFoundException("Report type not found"));
        checkRuleConflict(reportType, frequency, time, isActive);
        SchedulingRule rule = new SchedulingRule();
        rule.setReportType(reportType);
        rule.setFrequency(frequency);
        rule.setTime(time);
        rule.setActive(isActive);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        SchedulingRule saved = schedulingRuleRepository.save(rule);
        schedulerService.scheduleJob(saved);
        auditLogService.logChange("CREATE", saved, username);
        return saved;
    }

    /**
     * Edit an existing scheduling rule.
     */
    @Transactional
    public SchedulingRule editRule(Long ruleId, String frequency, String time, String username) {
        SchedulingRule rule = schedulingRuleRepository.findById(ruleId)
                .orElseThrow(() -> new SchedulingConflictException("Rule not found"));
        validateFrequency(frequency);
        validateTimeFormat(time);
        checkRuleConflict(rule.getReportType(), frequency, time, rule.isActive());
        rule.setFrequency(frequency);
        rule.setTime(time);
        rule.setUpdatedAt(LocalDateTime.now());
        SchedulingRule saved = schedulingRuleRepository.save(rule);
        schedulerService.rescheduleJob(saved);
        auditLogService.logChange("EDIT", saved, username);
        return saved;
    }

    /**
     * Deactivate a scheduling rule.
     */
    @Transactional
    public SchedulingRule deactivateRule(Long ruleId, String username) {
        SchedulingRule rule = schedulingRuleRepository.findById(ruleId)
                .orElseThrow(() -> new SchedulingConflictException("Rule not found"));
        rule.setActive(false);
        rule.setUpdatedAt(LocalDateTime.now());
        SchedulingRule saved = schedulingRuleRepository.save(rule);
        schedulerService.cancelJob(saved);
        auditLogService.logChange("DEACTIVATE", saved, username);
        return saved;
    }

    /**
     * Get scheduling rules by report type.
     */
    public List<SchedulingRule> getRulesByReportType(Long reportTypeId) {
        return schedulingRuleRepository.findByReportTypeId(reportTypeId);
    }

    /**
     * Validation for frequency.
     */
    private void validateFrequency(String frequency) {
        if (!ALLOWED_FREQUENCIES.contains(frequency.toLowerCase())) {
            throw new InvalidFrequencyException("Invalid frequency");
        }
    }

    /**
     * Validation for time format (24-hour HH:mm).
     */
    private void validateTimeFormat(String time) {
        try {
            TIME_FORMATTER.parse(time);
        } catch (Exception e) {
            throw new InvalidTimeFormatException("Invalid time format");
        }
    }

    /**
     * Check for scheduling rule conflict.
     */
    private void checkRuleConflict(ReportType reportType, String frequency, String time, boolean isActive) {
        Optional<SchedulingRule> existing = schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(reportType, frequency, time, isActive);
        if (existing.isPresent()) {
            throw new SchedulingConflictException("Scheduling rule conflict");
        }
    }
}
