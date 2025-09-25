package com.example.scheduler.controller;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller for schedule management
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a new schedule
     */
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.createSchedule(request));
    }

    /**
     * Edit an existing schedule
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> editSchedule(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(scheduleService.editSchedule(id, request));
    }

    /**
     * Delete a schedule
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(id));
    }

    /**
     * View all schedules
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> viewSchedules() {
        return ResponseEntity.ok(scheduleService.viewSchedules());
    }

    /**
     * Trigger report generation and delivery for a schedule
     */
    @PostMapping("/{id}/trigger")
    public ResponseEntity<TriggerReportResponse> triggerReport(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.triggerReport(id));
    }
}
