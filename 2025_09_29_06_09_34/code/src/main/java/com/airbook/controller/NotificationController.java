package com.airbook.controller;

import com.airbook.model.NotificationPreference;
import com.airbook.model.NotificationLog;
import com.airbook.service.NotificationService;
import com.airbook.service.PreferenceService;
import com.airbook.service.AirlineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for notification management operations
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private AirlineStatusService airlineStatusService;

    /**
     * Set notification preferences for a user
     */
    @PostMapping("/preferences")
    public ResponseEntity<String> setPreferences(@RequestBody NotificationPreference preference) {
        preferenceService.savePreferences(preference);
        return ResponseEntity.ok("SAVED");
    }

    /**
     * Get notification preferences for a user
     */
    @GetMapping("/preferences")
    public ResponseEntity<List<String>> getPreferences(@RequestParam String userId) {
        List<String> channels = preferenceService.getPreferences(userId);
        return ResponseEntity.ok(channels);
    }

    /**
     * Receive flight status update and trigger notification
     */
    @PostMapping("/airline/status")
    public ResponseEntity<String> receiveFlightStatusUpdate(@RequestBody AirlineStatusService.FlightStatusUpdate update) {
        notificationService.sendFlightStatusNotification(update);
        return ResponseEntity.ok("SENT");
    }

    /**
     * Get notification delivery logs for a user
     */
    @GetMapping("/logs")
    public ResponseEntity<List<NotificationLog>> getNotificationLogs(@RequestParam String userId) {
        List<NotificationLog> logs = notificationService.getNotificationLogs(userId);
        return ResponseEntity.ok(logs);
    }
}
