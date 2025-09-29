package com.airbook.service;

import com.airbook.model.NotificationLog;
import com.airbook.model.NotificationPreference;
import com.airbook.repository.NotificationLogRepository;
import com.airbook.service.PreferenceService;
import com.airbook.util.NotificationChannelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for notification logic, delivery, and logging
 */
@Service
public class NotificationService {
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private NotificationChannelClient notificationChannelClient;
    @Autowired
    private NotificationLogRepository notificationLogRepository;

    /**
     * Send flight status notification to user based on preferences
     */
    public void sendFlightStatusNotification(AirlineStatusService.FlightStatusUpdate update) {
        List<String> channels = preferenceService.getPreferences(update.getUserId());
        for (String channel : channels) {
            boolean delivered = notificationChannelClient.sendNotification(channel, update.getUserId(), update.getStatus(), update.getDetails());
            NotificationLog log = new NotificationLog();
            log.setLogId(java.util.UUID.randomUUID().toString());
            log.setUserId(update.getUserId());
            log.setType(channel);
            log.setStatus(delivered ? "DELIVERED" : "FAILED");
            log.setMessage(update.getStatus() + ": " + update.getDetails());
            log.setSentAt(java.time.LocalDateTime.now());
            notificationLogRepository.save(log);
        }
    }

    /**
     * Get notification delivery logs for a user
     */
    public List<NotificationLog> getNotificationLogs(String userId) {
        return notificationLogRepository.findLogsByUser(userId);
    }
}
