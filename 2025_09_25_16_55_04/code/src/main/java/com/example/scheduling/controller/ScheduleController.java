package com.example.scheduling.controller;

import com.example.scheduling.model.ScheduleRequest;
import com.example.scheduling.model.ScheduleResponse;
import com.example.scheduling.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * REST Controller for managing report schedules.
 * Exposes endpoints for creating, editing, deleting, and fetching schedules.
 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a new schedule.
     * @param request ScheduleRequest containing scheduling parameters.
     * @return ResponseEntity with ScheduleResponse.
     */
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleService.createSchedule(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Edit an existing schedule.
     * @param id Schedule ID.
     * @param request ScheduleRequest containing updated parameters.
     * @return ResponseEntity with ScheduleResponse.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> editSchedule(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleService.editSchedule(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a schedule.
     * @param id Schedule ID.
     * @return ResponseEntity with ScheduleResponse.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(@PathVariable Long id) {
        ScheduleResponse response = scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get details of a schedule.
     * @param id Schedule ID.
     * @return ResponseEntity with ScheduleResponse.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {
        Optional<ScheduleResponse> response = scheduleService.getSchedule(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
