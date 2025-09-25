package com.example.scheduledreporting.repository;

import com.example.scheduledreporting.entity.ScheduledReport;
import com.example.scheduledreporting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for ScheduledReport entity.
 */
@Repository
public interface ScheduledReportRepository extends JpaRepository<ScheduledReport, Integer> {
    List<ScheduledReport> findByUser(User user);
    Optional<ScheduledReport> findByIdAndUser(Integer id, User user);
    boolean existsByUserAndReportTypeAndScheduleTime(User user, String reportType, LocalTime scheduleTime);
}
