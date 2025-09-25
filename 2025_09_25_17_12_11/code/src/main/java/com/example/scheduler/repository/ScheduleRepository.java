package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Schedule entity.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserId(Long userId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Schedule s JOIN s.recipients r WHERE s.reportId = ?1 AND s.frequency = ?2 AND s.time = ?3 AND r.email IN ?4")
    boolean existsByReportIdAndFrequencyAndTimeAndRecipients(Long reportId, String frequency, String time, List<String> recipients);
}
