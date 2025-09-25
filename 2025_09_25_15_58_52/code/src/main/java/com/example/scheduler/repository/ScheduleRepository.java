package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Schedule entity.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // Additional query methods can be defined here
}
