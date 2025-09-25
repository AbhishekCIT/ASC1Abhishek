package com.example.scheduler.repository;

import com.example.scheduler.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Recipient entity.
 */
@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    List<Recipient> findByScheduleId(Long scheduleId);
    List<Recipient> findByScheduleIdAndEmailIn(Long scheduleId, List<String> emails);
}
