package com.example.airbooking.service;

import com.example.airbooking.model.NotificationSubscribeRequest;
import com.example.airbooking.model.NotificationSubscribeResponse;
import com.example.airbooking.entity.Subscription;
import com.example.airbooking.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for managing subscriptions and sending notifications.
 */
@Service
public class NotificationService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    /**
     * Subscribe to notifications for flight status changes.
     * @param request NotificationSubscribeRequest
     * @return NotificationSubscribeResponse
     */
    public NotificationSubscribeResponse subscribe(NotificationSubscribeRequest request) {
        // Validate email and flight number
        Subscription sub = new Subscription();
        sub.setSubscriptionId(UUID.randomUUID().toString());
        sub.setFlightNumber(request.getFlightNumber());
        sub.setEmail(request.getEmail());
        sub.setOptedIn(true);
        subscriptionRepository.save(sub);
        NotificationSubscribeResponse resp = new NotificationSubscribeResponse();
        resp.setSubscriptionId(sub.getSubscriptionId());
        resp.setStatus("SUBSCRIBED");
        return resp;
    }

    /**
     * Send notification to user (stub).
     * @param flightStatusChange status change info
     */
    public void sendNotification(String flightStatusChange) {
        // TODO: Integrate with email/push notification service
        System.out.println("Sending notification: " + flightStatusChange);
    }
}
