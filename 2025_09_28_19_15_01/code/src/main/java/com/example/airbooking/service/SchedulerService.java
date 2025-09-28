package com.example.airbooking.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Service for scheduling periodic status refreshes.
 */
@Service
public class SchedulerService {
    /**
     * Refresh flight status every 2 minutes.
     */
    @Scheduled(fixedRate = 120000)
    public void refreshStatus() {
        // TODO: Call FlightStatusService to refresh statuses
        System.out.println("Refreshing flight statuses...");
    }
}
