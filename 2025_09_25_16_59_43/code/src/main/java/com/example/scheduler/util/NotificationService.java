package com.example.scheduler.util;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.Notification;
import com.example.scheduler.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * Utility class for user notifications
 */
@Component
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    /**
     * Notify user of successful report delivery
     */
    public void notifySuccess(Schedule schedule) {
        Notification notification = new Notification();
        notification.setScheduleId(schedule.getId());
        notification.setStatus("success");
        notification.setMessage("Report delivered successfully");
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    /**
     * Notify user of report delivery failure
     */
    public void notifyFailure(Schedule schedule, String errorMessage) {
        Notification notification = new Notification();
        notification.setScheduleId(schedule.getId());
        notification.setStatus("failure");
        notification.setMessage(errorMessage);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }
}
