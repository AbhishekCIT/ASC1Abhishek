package com.example.airbooking.controller;

import com.example.airbooking.model.Notification;
import com.example.airbooking.model.Subscription;
import com.example.airbooking.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for notification operations.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /**
     * Subscribe to flight notifications.
     * @param subscribeRequest Request body containing userId, flightNumber, contact, type
     * @return Subscription
     */
    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody Map<String, String> subscribeRequest) {
        String userId = subscribeRequest.get("userId");
        String flightNumber = subscribeRequest.get("flightNumber");
        String contact = subscribeRequest.get("contact");
        String type = subscribeRequest.get("type");
        return notificationService.subscribe(userId, flightNumber, contact, type);
    }

    /**
     * Send notification to a subscription.
     * @param sendRequest Request body containing subscriptionId and message
     * @return Notification
     */
    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Map<String, String> sendRequest) {
        String subscriptionId = sendRequest.get("subscriptionId");
        String message = sendRequest.get("message");
        return notificationService.sendNotification(subscriptionId, message);
    }
}