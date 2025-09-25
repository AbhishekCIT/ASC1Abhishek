package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.ScheduleRecipient;
import com.example.scheduler.entity.Report;
import com.example.scheduler.model.ScheduleRequest;
import com.example.scheduler.model.ScheduleResponse;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.ReportRepository;
import com.example.scheduler.repository.ScheduleRecipientRepository;
import com.example.scheduler.util.EmailValidatorUtil;
import com.example.scheduler.util.TimeValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for scheduling business logic.
 */
@Service
public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleRecipientRepository scheduleRecipientRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ReportService reportService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    /**
     * Create a new schedule with validation.
     */
    @Transactional
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        validateScheduleRequest(request);
        // Check for duplicate schedule
        if (scheduleRepository.existsByReportIdAndFrequencyAndTimeAndRecipients(
                request.getReportId(), request.getFrequency(), request.getTime(), request.getRecipients())) {
            throw new RuntimeException("Duplicate schedule");
        }
        Schedule schedule = new Schedule();
        schedule.setReportId(request.getReportId());
        schedule.setFrequency(request.getFrequency());
        schedule.setTime(LocalTime.parse(request.getTime()));
        schedule.setStatus("CREATED");
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());
        schedule.setNextRun(getNextRunTime(request.getTime()));
        Schedule saved = scheduleRepository.save(schedule);
        // Save recipients
        List<ScheduleRecipient> recipients = request.getRecipients().stream()
                .map(email -> new ScheduleRecipient(saved, email))
                .collect(Collectors.toList());
        scheduleRecipientRepository.saveAll(recipients);
        notificationService.sendConfirmation(saved.getId(), "CREATED");
        logger.info("Schedule created: {}", saved.getId());
        return new ScheduleResponse(saved.getId(), "CREATED");
    }

    /**
     * Edit an existing schedule.
     */
    @Transactional
    public ScheduleResponse editSchedule(Long scheduleId, ScheduleRequest request) {
        validateScheduleRequest(request);
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        schedule.setFrequency(request.getFrequency());
        schedule.setTime(LocalTime.parse(request.getTime()));
        schedule.setUpdatedAt(LocalDateTime.now());
        schedule.setNextRun(getNextRunTime(request.getTime()));
        scheduleRecipientRepository.deleteByScheduleId(scheduleId);
        List<ScheduleRecipient> recipients = request.getRecipients().stream()
                .map(email -> new ScheduleRecipient(schedule, email))
                .collect(Collectors.toList());
        scheduleRecipientRepository.saveAll(recipients);
        scheduleRepository.save(schedule);
        notificationService.sendConfirmation(scheduleId, "UPDATED");
        logger.info("Schedule updated: {}", scheduleId);
        return new ScheduleResponse(scheduleId, "UPDATED");
    }

    /**
     * Delete a schedule.
     */
    @Transactional
    public ScheduleResponse deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        scheduleRecipientRepository.deleteByScheduleId(scheduleId);
        scheduleRepository.delete(schedule);
        notificationService.sendConfirmation(scheduleId, "DELETED");
        logger.info("Schedule deleted: {}", scheduleId);
        return new ScheduleResponse(scheduleId, "DELETED");
    }

    /**
     * List all schedules for a user.
     */
    public List<ScheduleResponse> listSchedules(Long userId) {
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        return schedules.stream().map(s -> {
            List<String> recipients = scheduleRecipientRepository.findByScheduleId(s.getId())
                    .stream().map(ScheduleRecipient::getEmail).collect(Collectors.toList());
            return new ScheduleResponse(s.getId(), s.getReportId(), s.getFrequency(), s.getTime().toString(), recipients, s.getStatus());
        }).collect(Collectors.toList());
    }

    /**
     * Trigger a schedule manually (internal use).
     */
    @Async
    @Transactional
    public ScheduleResponse triggerSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        Report report = reportRepository.findById(schedule.getReportId())
                .orElseThrow(() -> new RuntimeException("Report not found"));
        List<String> recipients = scheduleRecipientRepository.findByScheduleId(scheduleId)
                .stream().map(ScheduleRecipient::getEmail).collect(Collectors.toList());
        // Generate report
        byte[] reportFile = reportService.generateReport(report);
        // Send email
        emailService.sendReport(reportFile, recipients);
        // Log execution
        schedule.setStatus("SENT");
        schedule.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(schedule);
        logger.info("Schedule triggered and report sent: {}", scheduleId);
        return new ScheduleResponse(scheduleId, "TRIGGERED");
    }

    /**
     * Validate schedule request fields.
     */
    private void validateScheduleRequest(ScheduleRequest request) {
        if (!Arrays.asList("DAILY", "WEEKLY", "MONTHLY", "CUSTOM").contains(request.getFrequency())) {
            logger.warn("Invalid frequency: {}", request.getFrequency());
            throw new RuntimeException("Invalid frequency");
        }
        if (!TimeValidatorUtil.isValidFutureTime(request.getTime())) {
            logger.warn("Invalid or past time: {}", request.getTime());
            throw new RuntimeException("Invalid or past time");
        }
        for (String email : request.getRecipients()) {
            if (!EmailValidatorUtil.isValidEmail(email)) {
                logger.warn("Invalid email: {}", email);
                throw new RuntimeException("Invalid email address");
            }
        }
    }

    /**
     * Get next run time from time string (HH:mm).
     */
    private LocalDateTime getNextRunTime(String time) {
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.withHour(localTime.getHour()).withMinute(localTime.getMinute()).withSecond(0).withNano(0);
        if (nextRun.isBefore(now)) {
            nextRun = nextRun.plusDays(1);
        }
        return nextRun;
    }
}
