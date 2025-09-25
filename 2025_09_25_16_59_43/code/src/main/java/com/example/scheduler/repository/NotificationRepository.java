package com.example.scheduler.repository;

import com.example.scheduler.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for NOTIFICATIONS table
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
