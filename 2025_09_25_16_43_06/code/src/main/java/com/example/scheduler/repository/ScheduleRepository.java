package com.example.scheduler.repository;

import com.example.scheduler.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Schedule entity data access.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Custom query methods can be added here
}
