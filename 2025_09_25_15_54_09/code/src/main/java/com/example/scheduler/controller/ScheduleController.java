package com.example.scheduler.controller;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for managing report schedules.
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a new schedule for automated report delivery.
     * @param request ScheduleRequest containing schedule details
     * @return ScheduleResponse with scheduleId and status
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all schedules for the authenticated user.
     * @return List of ScheduleResponse
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ScheduleResponse>> getSchedules() {
        List<ScheduleResponse> schedules = scheduleService.getSchedulesForCurrentUser();
        return ResponseEntity.ok(schedules);
    }

    /**
     * Manually trigger a report for a given schedule.
     * @param scheduleId The schedule ID
     * @return TriggerReportResponse with delivery status and timestamp
     */
    @PostMapping("/{scheduleId}/trigger")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TriggerReportResponse> triggerReport(@PathVariable String scheduleId) {
        TriggerReportResponse response = scheduleService.triggerReport(scheduleId);
        return ResponseEntity.ok(response);
    }
}
