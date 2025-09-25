package com.example.scheduler.controller;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.NotificationRequest;
import com.example.scheduler.model.NotificationResponse;
import com.example.scheduler.service.ScheduleService;
import com.example.scheduler.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing report schedules.
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private NotificationService notificationService;

    /**
     * Create a new schedule.
     */
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(request));
    }

    /**
     * Edit an existing schedule.
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> editSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.editSchedule(scheduleId, request));
    }

    /**
     * Delete a schedule.
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId));
    }

    /**
     * List all schedules for a user.
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> listSchedules(@RequestParam Long userId) {
        return ResponseEntity.ok(scheduleService.listSchedules(userId));
    }

    /**
     * Trigger a schedule manually (internal use).
     */
    @PostMapping("/{scheduleId}/trigger")
    public ResponseEntity<ScheduleResponse> triggerSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.triggerSchedule(scheduleId));
    }

    /**
     * Send notification to user.
     */
    @PostMapping("/notifications")
    public ResponseEntity<NotificationResponse> notifyUser(@RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }
}
