package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * Manages backend scheduled jobs for report generation.
 */
@Component
public class SchedulerJobManager {
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private ReportGenerator reportGenerator;

    /**
     * Create a scheduled job for the given schedule and recipients.
     */
    public void createJob(Schedule schedule, List<Recipient> recipients) {
        // Schedule the report generation using Spring TaskScheduler (or Quartz)
        Runnable task = () -> reportGenerator.scheduleReportGeneration(schedule, recipients);
        taskScheduler.schedule(task, schedule.getNextRun().atZone(ZoneId.systemDefault()).toInstant());
    }
}
