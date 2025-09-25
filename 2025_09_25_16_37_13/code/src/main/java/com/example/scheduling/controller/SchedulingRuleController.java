package com.example.scheduling.controller;

import com.example.scheduling.model.SchedulingRule;
import com.example.scheduling.service.SchedulingRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * REST Controller for managing scheduling rules for automated reports.
 */
@RestController
@RequestMapping("/api/scheduling-rules")
public class SchedulingRuleController {
    @Autowired
    private SchedulingRuleService schedulingRuleService;

    /**
     * Create a new scheduling rule.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS_ANALYST')")
    @PostMapping
    public ResponseEntity<?> createSchedulingRule(@RequestBody SchedulingRuleRequest request, Principal principal) {
        SchedulingRule rule = schedulingRuleService.createRule(
                request.getReportTypeId(),
                request.getFrequency(),
                request.getTime(),
                request.isActive(),
                principal.getName()
        );
        return ResponseEntity.ok(new SchedulingRuleResponse(rule.getId(), "scheduled"));
    }

    /**
     * Edit an existing scheduling rule.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS_ANALYST')")
    @PutMapping("/{id}")
    public ResponseEntity<?> editSchedulingRule(@PathVariable Long id, @RequestBody SchedulingRuleEditRequest request, Principal principal) {
        SchedulingRule rule = schedulingRuleService.editRule(
                id,
                request.getFrequency(),
                request.getTime(),
                principal.getName()
        );
        return ResponseEntity.ok(new SchedulingRuleResponse(rule.getId(), "updated"));
    }

    /**
     * Deactivate a scheduling rule.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS_ANALYST')")
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateSchedulingRule(@PathVariable Long id, Principal principal) {
        SchedulingRule rule = schedulingRuleService.deactivateRule(id, principal.getName());
        return ResponseEntity.ok(new SchedulingRuleResponse(rule.getId(), "deactivated"));
    }

    /**
     * Get scheduling rules by report type.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'BUSINESS_ANALYST')")
    @GetMapping
    public ResponseEntity<List<SchedulingRule>> getSchedulingRules(@RequestParam Long reportTypeId) {
        List<SchedulingRule> rules = schedulingRuleService.getRulesByReportType(reportTypeId);
        return ResponseEntity.ok(rules);
    }

    // Request and Response DTOs
    public static class SchedulingRuleRequest {
        private Long reportTypeId;
        private String frequency;
        private String time;
        private boolean isActive;
        // Getters and Setters
        public Long getReportTypeId() { return reportTypeId; }
        public void setReportTypeId(Long reportTypeId) { this.reportTypeId = reportTypeId; }
        public String getFrequency() { return frequency; }
        public void setFrequency(String frequency) { this.frequency = frequency; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        public boolean isActive() { return isActive; }
        public void setActive(boolean active) { isActive = active; }
    }
    public static class SchedulingRuleEditRequest {
        private String frequency;
        private String time;
        // Getters and Setters
        public String getFrequency() { return frequency; }
        public void setFrequency(String frequency) { this.frequency = frequency; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
    }
    public static class SchedulingRuleResponse {
        private Long id;
        private String status;
        public SchedulingRuleResponse(Long id, String status) {
            this.id = id;
            this.status = status;
        }
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
