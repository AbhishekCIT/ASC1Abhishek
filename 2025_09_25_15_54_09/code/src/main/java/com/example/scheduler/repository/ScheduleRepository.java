package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Schedule entity.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    List<Schedule> findByUser(User user);
}
