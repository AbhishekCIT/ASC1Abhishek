package com.example.airbooking.service;

import com.example.airbooking.model.Notification;
import com.example.airbooking.model.Subscription;
import com.example.airbooking.repository.NotificationRepository;
import com.example.airbooking.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for notification subscriptions and delivery.
 */
@Service
public class NotificationService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Subscribes a user to flight notifications.
     * @param userId User ID
     * @param flightNumber Flight number
     * @param contact Contact detail (email/phone)
     * @param type Notification type
     * @return Subscription
     */
    public Subscription subscribe(String userId, String flightNumber, String contact, String type) {
        if (contact == null || contact.isEmpty()) {
            throw new IllegalArgumentException("Invalid contact details.");
        }
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(UUID.randomUUID().toString());
        subscription.setUserId(userId);
        subscription.setFlightNumber(flightNumber);
        subscription.setContact(contact);
        subscription.setType(type);
        return subscriptionRepository.save(subscription);
    }

    /**
     * Sends a notification to a subscription.
     * @param subscriptionId Subscription ID
     * @param message Message to send
     * @return Notification
     */
    public Notification sendNotification(String subscriptionId, String message) {
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setSubscriptionId(subscriptionId);
        notification.setMessage(message);
        notification.setSentAt(LocalDateTime.now());
        notification.setDeliveryStatus("SENT");
        // Simulate sending (integrate with provider in real implementation)
        return notificationRepository.save(notification);
    }
}