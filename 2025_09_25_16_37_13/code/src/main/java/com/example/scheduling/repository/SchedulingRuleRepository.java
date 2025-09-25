package com.example.scheduling.repository;

import com.example.scheduling.model.SchedulingRule;
import com.example.scheduling.model.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for SchedulingRule entity.
 */
@Repository
public interface SchedulingRuleRepository extends JpaRepository<SchedulingRule, Long> {
    List<SchedulingRule> findByReportTypeAndIsActive(ReportType reportType, boolean isActive);
    List<SchedulingRule> findByReportTypeId(Long reportTypeId);
    Optional<SchedulingRule> findByReportTypeAndFrequencyAndTimeAndIsActive(ReportType reportType, String frequency, String time, boolean isActive);
}
