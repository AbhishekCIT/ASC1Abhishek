package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Generates reports as per schedule (stub for integration).
 */
@Component
public class ReportGenerator {
    @Autowired
    private ReportDeliveryService reportDeliveryService;

    /**
     * Schedule report generation and delivery.
     */
    public void scheduleReportGeneration(Schedule schedule, List<Recipient> recipients) {
        // TODO: Integrate with actual report generation logic
        // For now, just call delivery service
        reportDeliveryService.deliverReport(schedule, recipients);
    }
}
