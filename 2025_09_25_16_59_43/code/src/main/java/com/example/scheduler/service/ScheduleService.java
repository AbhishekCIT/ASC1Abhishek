package com.example.scheduler.service;

import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.model.TriggerReportResponse;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.Recipient;
import com.example.scheduler.entity.Notification;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.NotificationRepository;
import com.example.scheduler.util.EmailService;
import com.example.scheduler.util.NotificationService;
import com.example.scheduler.util.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Business logic for scheduling and triggering reports
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationRepository notificationRepository;

    private static final Set<String> VALID_FREQUENCIES = new HashSet<>(Arrays.asList("daily", "weekly", "monthly", "custom"));

    /**
     * Create a new schedule
     */
    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        validateScheduleRequest(request);
        if (scheduleRepository.existsByReportIdAndRecipients(request.getReportId(), String.join(",", request.getRecipients()))) {
            throw new RuntimeException("Duplicate schedule for this report and recipient");
        }
        Schedule schedule = new Schedule();
        schedule.setReportId(request.getReportId());
        schedule.setFrequency(request.getFrequency());
        schedule.setTime(request.getTime());
        schedule.setRecipients(String.join(",", request.getRecipients()));
        schedule.setNextRun(calculateNextRun(request.getFrequency(), request.getTime()));
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule.getId(), "created");
    }

    /**
     * Edit an existing schedule
     */
    @Transactional
    public ScheduleResponse editSchedule(Long id, ScheduleRequest request) {
        validateScheduleRequest(request);
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setFrequency(request.getFrequency());
        schedule.setTime(request.getTime());
        schedule.setRecipients(String.join(",", request.getRecipients()));
        schedule.setNextRun(calculateNextRun(request.getFrequency(), request.getTime()));
        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule.getId(), "updated");
    }

    /**
     * Delete a schedule
     */
    @Transactional
    public ScheduleResponse deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRepository.delete(schedule);
        return new ScheduleResponse(id, "deleted");
    }

    /**
     * View all schedules
     */
    @Transactional(readOnly = true)
    public List<ScheduleResponse> viewSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(s -> new ScheduleResponse(s.getId(), s.getReportId(), s.getFrequency(), s.getTime(), Arrays.asList(s.getRecipients().split(",")), s.getNextRun()))
                .collect(Collectors.toList());
    }

    /**
     * Trigger report generation and delivery for a schedule
     */
    @Transactional
    public TriggerReportResponse triggerReport(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        // Generate report
        byte[] reportData;
        try {
            reportData = reportService.generateReport(schedule.getReportId());
        } catch (Exception e) {
            notificationService.notifyFailure(schedule, "Report generation failed");
            throw new RuntimeException("Report generation failed");
        }
        // Send report
        boolean deliverySuccess = emailService.sendReport(schedule.getRecipients().split(","), reportData);
        if (!deliverySuccess) {
            notificationService.notifyFailure(schedule, "Report delivery failed");
            throw new RuntimeException("Report delivery failed");
        }
        notificationService.notifySuccess(schedule);
        return new TriggerReportResponse("success", LocalDateTime.now());
    }

    /**
     * Validate schedule request
     */
    private void validateScheduleRequest(ScheduleRequest request) {
        if (!VALID_FREQUENCIES.contains(request.getFrequency())) {
            throw new RuntimeException("Invalid frequency value");
        }
        for (String email : request.getRecipients()) {
            if (!EmailService.isValidEmail(email)) {
                throw new RuntimeException("Invalid recipient email address");
            }
        }
        // TODO: Validate reportId exists using reportService
    }

    /**
     * Calculate next run datetime based on frequency and time
     */
    private LocalDateTime calculateNextRun(String frequency, String time) {
        // Simplified next run calculation
        LocalDateTime now = LocalDateTime.now();
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        LocalDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(0);
        if (nextRun.isBefore(now)) {
            switch (frequency) {
                case "daily":
                    nextRun = nextRun.plusDays(1);
                    break;
                case "weekly":
                    nextRun = nextRun.plusWeeks(1);
                    break;
                case "monthly":
                    nextRun = nextRun.plusMonths(1);
                    break;
                default:
                    nextRun = nextRun.plusDays(1);
            }
        }
        return nextRun;
    }
}
