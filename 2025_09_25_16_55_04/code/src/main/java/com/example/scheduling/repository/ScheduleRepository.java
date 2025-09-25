package com.example.scheduling.repository;

import com.example.scheduling.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ScheduleEntity CRUD operations.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    // Additional query methods can be defined here
}
