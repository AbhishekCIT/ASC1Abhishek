package com.example.flightbooking.repository;

import com.example.flightbooking.model.NotificationSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for NotificationSubscription entity.
 */
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, Long> {
}
