package com.example.scheduling.repository;

import com.example.scheduling.model.ReportType;
import com.example.scheduling.model.SchedulingRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for SchedulingRuleRepository using H2 in-memory database.
 */
@DataJpaTest
public class SchedulingRuleRepositoryTest {

    @Autowired
    private SchedulingRuleRepository schedulingRuleRepository;

    private ReportType reportType;
    private SchedulingRule rule;

    @BeforeEach
    void setUp() {
        reportType = new ReportType();
        reportType.setName("Sales");
        // Save ReportType first
        // Use EntityManager if needed, but for simplicity, rely on cascade
        rule = new SchedulingRule();
        rule.setReportType(reportType);
        rule.setFrequency("daily");
        rule.setTime("14:00");
        rule.setActive(true);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        schedulingRuleRepository.save(rule);
    }

    /**
     * Test findByReportTypeAndIsActive returns correct rules.
     */
    @Test
    void testFindByReportTypeAndIsActive() {
        List<SchedulingRule> rules = schedulingRuleRepository.findByReportTypeAndIsActive(reportType, true);
        assertFalse(rules.isEmpty());
        assertEquals("daily", rules.get(0).getFrequency());
    }

    /**
     * Test findByReportTypeId returns correct rules.
     */
    @Test
    void testFindByReportTypeId() {
        Long reportTypeId = rule.getReportType().getId();
        List<SchedulingRule> rules = schedulingRuleRepository.findByReportTypeId(reportTypeId);
        assertFalse(rules.isEmpty());
        assertEquals("daily", rules.get(0).getFrequency());
    }

    /**
     * Test findByReportTypeAndFrequencyAndTimeAndIsActive returns correct rule.
     */
    @Test
    void testFindByReportTypeAndFrequencyAndTimeAndIsActive() {
        Optional<SchedulingRule> found = schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(reportType, "daily", "14:00", true);
        assertTrue(found.isPresent());
        assertEquals("daily", found.get().getFrequency());
    }

    /**
     * Test findByReportTypeAndFrequencyAndTimeAndIsActive returns empty for non-existent rule (edge case).
     */
    @Test
    void testFindByReportTypeAndFrequencyAndTimeAndIsActive_NotFound() {
        Optional<SchedulingRule> found = schedulingRuleRepository.findByReportTypeAndFrequencyAndTimeAndIsActive(reportType, "weekly", "09:00", true);
        assertFalse(found.isPresent());
    }
}
