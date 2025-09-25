package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for SCHEDULES table
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByReportIdAndRecipients(Long reportId, String recipients);
}
