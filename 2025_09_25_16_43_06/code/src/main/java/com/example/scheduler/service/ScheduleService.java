package com.example.scheduler.service;

import com.example.scheduler.model.Schedule;
import com.example.scheduler.model.User;
import com.example.scheduler.model.Report;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.util.EmailValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for business logic and validations related to scheduling.
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmailValidatorUtil emailValidatorUtil;

    /**
     * Create a new schedule after validation.
     */
    @Transactional
    public Schedule createSchedule(Schedule schedule) {
        validateSchedule(schedule);
        schedule.setStatus("Scheduled");
        return scheduleRepository.save(schedule);
    }

    /**
     * Edit an existing schedule.
     */
    @Transactional
    public Schedule editSchedule(Long scheduleId, Schedule schedule) {
        Optional<Schedule> existingOpt = scheduleRepository.findById(scheduleId);
        if (!existingOpt.isPresent()) {
            throw new RuntimeException("Schedule not found.");
        }
        Schedule existing = existingOpt.get();
        if (schedule.getFrequency() != null) {
            validateFrequency(schedule.getFrequency());
            existing.setFrequency(schedule.getFrequency());
        }
        if (schedule.getRecipients() != null) {
            for (String email : schedule.getRecipients()) {
                if (!emailValidatorUtil.isValid(email)) {
                    throw new RuntimeException("Invalid recipient email address.");
                }
            }
            existing.setRecipients(schedule.getRecipients());
        }
        return scheduleRepository.save(existing);
    }

    /**
     * Cancel a schedule.
     */
    @Transactional
    public Schedule cancelSchedule(Long scheduleId) {
        Optional<Schedule> existingOpt = scheduleRepository.findById(scheduleId);
        if (!existingOpt.isPresent()) {
            throw new RuntimeException("Schedule not found.");
        }
        Schedule existing = existingOpt.get();
        existing.setStatus("Cancelled");
        return scheduleRepository.save(existing);
    }

    /**
     * Get all schedules for the current user (stubbed for demo).
     */
    public List<Schedule> getSchedulesForCurrentUser() {
        // In real implementation, fetch userId from security context
        return scheduleRepository.findAll();
    }

    /**
     * Validate schedule fields.
     */
    private void validateSchedule(Schedule schedule) {
        validateFrequency(schedule.getFrequency());
        if (schedule.getRecipients() == null || schedule.getRecipients().isEmpty()) {
            throw new RuntimeException("Recipients must be provided.");
        }
        for (String email : schedule.getRecipients()) {
            if (!emailValidatorUtil.isValid(email)) {
                throw new RuntimeException("Invalid recipient email address.");
            }
        }
        LocalDate start = schedule.getStartDate();
        LocalDate end = schedule.getEndDate();
        if (start.isAfter(end)) {
            throw new RuntimeException("Start date after end date.");
        }
        // Additional validations as per requirements
    }

    /**
     * Validate frequency value.
     */
    private void validateFrequency(String frequency) {
        if (!("daily".equalsIgnoreCase(frequency) || "weekly".equalsIgnoreCase(frequency) || "monthly".equalsIgnoreCase(frequency))) {
            throw new RuntimeException("Invalid frequency option.");
        }
    }
}
