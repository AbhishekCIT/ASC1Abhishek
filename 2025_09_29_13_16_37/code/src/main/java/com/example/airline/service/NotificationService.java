package com.example.airline.service;

import com.example.airline.model.User;
import com.example.airline.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    /**
     * Sends booking confirmation notification to the user.
     * @param user User
     * @param booking Booking
     */
    public void sendBookingConfirmation(User user, Booking booking) {
        // Integrate with Email/SMS provider
        // For demo, just print to console
        System.out.println("Notification sent to " + user.getEmail() + ": Booking " + booking.getBookingId() + " confirmed.");
    }

    /**
     * Sends a generic notification (email/SMS) to the user.
     * @param userId User ID
     * @param type Notification type (EMAIL/SMS)
     * @param message Message content
     */
    public void sendNotification(String userId, String type, String message) {
        // Integrate with Email/SMS provider
        // For demo, just print to console
        System.out.println("Notification sent to userId=" + userId + " via " + type + ": " + message);
    }
}