package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.Recipient;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Delivers generated reports via email or download link (stub for integration).
 */
@Component
public class ReportDeliveryService {
    public void deliverReport(Schedule schedule, List<Recipient> recipients) {
        // TODO: Implement actual report delivery logic (email, download link, etc.)
        System.out.printf("[DELIVERY] Delivering report for scheduleId=%d to %d recipients\n", schedule.getId(), recipients.size());
    }
}
