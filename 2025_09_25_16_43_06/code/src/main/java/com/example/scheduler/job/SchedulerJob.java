package com.example.scheduler.job;

import com.example.scheduler.service.ScheduleService;
import com.example.scheduler.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Background job to trigger scheduled reports.
 */
@Component
public class SchedulerJob {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ReportService reportService;

    /**
     * Runs every minute to check for due schedules and trigger report generation.
     */
    @Scheduled(cron = "0 * * * * *") // Every minute
    public void runScheduler() {
        // Fetch due schedules and trigger report generation
        // This is a stub implementation
        // List<Schedule> dueSchedules = scheduleService.getDueSchedules();
        // for (Schedule schedule : dueSchedules) {
        //     reportService.generateReport(new ReportGenerationRequest(schedule.getScheduleId()));
        // }
    }
}
