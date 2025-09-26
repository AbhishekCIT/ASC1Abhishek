package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.NotificationSubscriptionRepository;
import com.example.flightbooking.repository.UserRepository;
import com.example.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for managing user notifications for flight status updates.
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationSubscriptionRepository notificationSubscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserService userService;

    /**
     * Subscribe a user to flight status notifications.
     */
    public NotificationSubscription subscribe(NotificationSubscriptionRequest request, String token) {
        User user = userService.authenticate(token);
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new RuntimeException("Flight not found"));
        NotificationSubscription subscription = new NotificationSubscription();
        subscription.setUser(user);
        subscription.setFlight(flight);
        subscription.setSubscribedAt(LocalDateTime.now());
        subscription.setStatus("ACTIVE");
        return notificationSubscriptionRepository.save(subscription);
    }

    /**
     * Send notification to user (stub implementation).
     */
    public void sendNotification(User user, FlightStatusUpdate update) {
        // Integrate with notification gateway (email/SMS/push)
        // For demonstration, just print to console
        System.out.println("Notification sent to " + user.getEmail() + ": " + update.getMessage());
    }
}
