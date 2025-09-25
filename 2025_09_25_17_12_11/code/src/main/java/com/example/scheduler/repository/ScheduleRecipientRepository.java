package com.example.scheduler.repository;

import com.example.scheduler.entity.ScheduleRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for ScheduleRecipient entity.
 */
@Repository
public interface ScheduleRecipientRepository extends JpaRepository<ScheduleRecipient, Long> {
    List<ScheduleRecipient> findByScheduleId(Long scheduleId);
    void deleteByScheduleId(Long scheduleId);
}
