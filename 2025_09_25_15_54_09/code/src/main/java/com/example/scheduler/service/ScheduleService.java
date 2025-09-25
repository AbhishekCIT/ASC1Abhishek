package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.util.EmailUtil;
import com.example.scheduler.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for handling schedule logic, validation, persistence, and triggering reports.
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TaskScheduler taskScheduler;

    /**
     * Create a new schedule after validation and persist it.
     * @param request ScheduleRequest
     * @return ScheduleResponse
     */
    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        validateScheduleRequest(request);
        // Assume current user is fetched from security context
        User user = getCurrentUser();
        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setReportId(request.getReportId());
        schedule.setFrequency(request.getFrequency());
        schedule.setTime(request.getTime());
        schedule.setDeliveryMethods(request.getDeliveryMethods());
        schedule.setRecipients(request.getRecipients());
        schedule.setStatus("ACTIVE");
        schedule = scheduleRepository.save(schedule);
        registerJob(schedule);
        return new ScheduleResponse(schedule.getScheduleId(), "CREATED");
    }

    /**
     * Get all schedules for the currently authenticated user.
     * @return List of ScheduleResponse
     */
    public List<ScheduleResponse> getSchedulesForCurrentUser() {
        User user = getCurrentUser();
        List<Schedule> schedules = scheduleRepository.findByUser(user);
        return schedules.stream()
                .map(s -> new ScheduleResponse(s.getScheduleId(), s.getReportId(), s.getFrequency(), s.getTime(), s.getDeliveryMethods(), s.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * Manually trigger a report for a given schedule.
     * @param scheduleId Schedule ID
     * @return TriggerReportResponse
     */
    public TriggerReportResponse triggerReport(String scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        try {
            byte[] reportData = reportService.generateReport(schedule.getReportId());
            deliveryService.deliverReport(schedule, reportData);
            notificationService.notifyUser(schedule, true, null);
            return new TriggerReportResponse("SUCCESS", new Date());
        } catch (Exception ex) {
            notificationService.notifyUser(schedule, false, ex.getMessage());
            throw new RuntimeException("Failed to generate or deliver report: " + ex.getMessage());
        }
    }

    /**
     * Validate the schedule request as per business rules.
     */
    private void validateScheduleRequest(ScheduleRequest request) {
        if (!reportService.isValidReportTemplate(request.getReportId())) {
            throw new IllegalArgumentException("Invalid report template");
        }
        if (!Arrays.asList("DAILY", "WEEKLY", "MONTHLY").contains(request.getFrequency())) {
            throw new IllegalArgumentException("Invalid frequency");
        }
        if (!TimeUtil.isValidTime(request.getTime())) {
            throw new IllegalArgumentException("Invalid time");
        }
        if (request.getDeliveryMethods() == null || request.getDeliveryMethods().isEmpty()) {
            throw new IllegalArgumentException("At least one delivery method required");
        }
        for (String method : request.getDeliveryMethods()) {
            if ("EMAIL".equals(method)) {
                for (String recipient : request.getRecipients()) {
                    if (!EmailUtil.isValidEmail(recipient)) {
                        throw new IllegalArgumentException("Invalid email address: " + recipient);
                    }
                }
            }
        }
    }

    /**
     * Register the schedule as a job in the scheduler.
     */
    private void registerJob(Schedule schedule) {
        // Implementation: Register with Spring TaskScheduler or Quartz
        // This is a placeholder for actual scheduling logic
    }

    /**
     * Get the currently authenticated user.
     * (Stub for demonstration; real implementation would use Spring Security)
     */
    private User getCurrentUser() {
        // In real implementation, fetch from SecurityContextHolder
        return new User("user-1", "user@domain.com");
    }
}
