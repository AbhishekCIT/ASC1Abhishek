package com.example.scheduling.service;

import com.example.scheduling.exception.*;
import com.example.scheduling.model.ReportType;
import com.example.scheduling.model.SchedulingRule;
import com.example.scheduling.repository.SchedulingRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SchedulingRuleService business logic and validations.
 */
public class SchedulingRuleServiceTest {

    @Mock
    private SchedulingRuleRepository schedulingRuleRepository;
    @Mock
    private ReportTypeService reportTypeService;
    @Mock
    private SchedulerService schedulerService;
    @Mock
    private AuditLogService auditLogService;

    @InjectMocks
    private SchedulingRuleService schedulingRuleService;

    private ReportType reportType;
    private SchedulingRule rule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reportType = new ReportType();
        reportType.setId(1L);
        reportType.setName("Sales");
        rule = new SchedulingRule();
        rule.setId(1L);
        rule.setReportType(reportType);
        rule.setFrequency("daily");
        rule.setTime("14:00");
        rule.setActive(true);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Test creating a scheduling rule with valid input.
     */
    @Test
    void testCreateRule_Success() {
        when(reportTypeService.getReportTypeById(1L)).thenReturn(Optional.of(reportType));
        when(schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(any(), anyString(), anyString(), anyBoolean())).thenReturn(Optional.empty());
        when(schedulingRuleRepository.save(any(SchedulingRule.class))).thenReturn(rule);

        SchedulingRule result = schedulingRuleService.createRule(1L, "daily", "14:00", true, "admin");
        assertNotNull(result);
        assertEquals("daily", result.getFrequency());
        verify(schedulerService).scheduleJob(result);
        verify(auditLogService).logChange(eq("CREATE"), eq(result), eq("admin"));
    }

    /**
     * Test creating a scheduling rule with invalid frequency (edge case).
     */
    @Test
    void testCreateRule_InvalidFrequency() {
        Exception ex = assertThrows(InvalidFrequencyException.class, () ->
            schedulingRuleService.createRule(1L, "yearly", "14:00", true, "admin")
        );
        assertEquals("Invalid frequency", ex.getMessage());
    }

    /**
     * Test creating a scheduling rule with invalid time format (edge case).
     */
    @Test
    void testCreateRule_InvalidTimeFormat() {
        Exception ex = assertThrows(InvalidTimeFormatException.class, () ->
            schedulingRuleService.createRule(1L, "daily", "99:99", true, "admin")
        );
        assertEquals("Invalid time format", ex.getMessage());
    }

    /**
     * Test creating a scheduling rule with non-existent report type (error-handling).
     */
    @Test
    void testCreateRule_ReportTypeNotFound() {
        when(reportTypeService.getReportTypeById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(ReportTypeNotFoundException.class, () ->
            schedulingRuleService.createRule(2L, "daily", "14:00", true, "admin")
        );
        assertEquals("Report type not found", ex.getMessage());
    }

    /**
     * Test creating a scheduling rule with a conflicting rule (boundary condition).
     */
    @Test
    void testCreateRule_Conflict() {
        when(reportTypeService.getReportTypeById(1L)).thenReturn(Optional.of(reportType));
        when(schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(any(), anyString(), anyString(), anyBoolean())).thenReturn(Optional.of(rule));
        Exception ex = assertThrows(SchedulingConflictException.class, () ->
            schedulingRuleService.createRule(1L, "daily", "14:00", true, "admin")
        );
        assertEquals("Scheduling rule conflict", ex.getMessage());
    }

    /**
     * Test editing a scheduling rule with valid input.
     */
    @Test
    void testEditRule_Success() {
        when(schedulingRuleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(any(), anyString(), anyString(), anyBoolean())).thenReturn(Optional.empty());
        when(schedulingRuleRepository.save(any(SchedulingRule.class))).thenReturn(rule);

        SchedulingRule result = schedulingRuleService.editRule(1L, "weekly", "09:00", "admin");
        assertNotNull(result);
        assertEquals("weekly", result.getFrequency());
        assertEquals("09:00", result.getTime());
        verify(schedulerService).rescheduleJob(result);
        verify(auditLogService).logChange(eq("EDIT"), eq(result), eq("admin"));
    }

    /**
     * Test editing a scheduling rule that does not exist (error-handling).
     */
    @Test
    void testEditRule_NotFound() {
        when(schedulingRuleRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(SchedulingConflictException.class, () ->
            schedulingRuleService.editRule(2L, "weekly", "09:00", "admin")
        );
        assertEquals("Rule not found", ex.getMessage());
    }

    /**
     * Test deactivating a scheduling rule with valid input.
     */
    @Test
    void testDeactivateRule_Success() {
        when(schedulingRuleRepository.findById(1L)).thenReturn(Optional.of(rule));
        when(schedulingRuleRepository.save(any(SchedulingRule.class))).thenReturn(rule);

        SchedulingRule result = schedulingRuleService.deactivateRule(1L, "admin");
        assertNotNull(result);
        assertFalse(result.isActive());
        verify(schedulerService).cancelJob(result);
        verify(auditLogService).logChange(eq("DEACTIVATE"), eq(result), eq("admin"));
    }

    /**
     * Test deactivating a scheduling rule that does not exist (error-handling).
     */
    @Test
    void testDeactivateRule_NotFound() {
        when(schedulingRuleRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(SchedulingConflictException.class, () ->
            schedulingRuleService.deactivateRule(2L, "admin")
        );
        assertEquals("Rule not found", ex.getMessage());
    }

    /**
     * Test getting scheduling rules by report type (normal scenario).
     */
    @Test
    void testGetRulesByReportType_Success() {
        when(schedulingRuleRepository.findByReportTypeId(1L)).thenReturn(List.of(rule));
        List<SchedulingRule> rules = schedulingRuleService.getRulesByReportType(1L);
        assertNotNull(rules);
        assertEquals(1, rules.size());
        assertEquals("daily", rules.get(0).getFrequency());
    }
}
