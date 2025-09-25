package com.example.scheduler.controller;

import com.example.scheduler.model.Schedule;
import com.example.scheduler.model.NotificationRequest;
import com.example.scheduler.model.ReportGenerationRequest;
import com.example.scheduler.service.ScheduleService;
import com.example.scheduler.service.NotificationService;
import com.example.scheduler.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for scheduling reports and related operations.
 */
@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ReportService reportService;

    /**
     * Create a new schedule for automated report delivery.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.createSchedule(schedule));
    }

    /**
     * Edit an existing schedule.
     */
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> editSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.editSchedule(scheduleId, schedule));
    }

    /**
     * Cancel a schedule.
     */
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> cancelSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.cancelSchedule(scheduleId));
    }

    /**
     * View all schedules for the authenticated user.
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> viewSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedulesForCurrentUser());
    }

    /**
     * Notify recipients about report delivery status.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/notifications")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.sendNotification(request));
    }

    /**
     * Generate a report for a schedule.
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reports/generate")
    public ResponseEntity<?> generateReport(@RequestBody ReportGenerationRequest request) {
        return ResponseEntity.ok(reportService.generateReport(request));
    }
}
