package com.example.scheduler.service;

import com.example.scheduler.entity.Schedule;
import org.springframework.stereotype.Service;

/**
 * Service for report delivery logic (e.g., email, download link).
 */
@Service
public class DeliveryService {
    /**
     * Deliver the report based on schedule's delivery methods.
     * @param schedule Schedule entity
     * @param reportData Report data as byte array
     */
    public void deliverReport(Schedule schedule, byte[] reportData) {
        // Placeholder: Implement actual delivery logic (e.g., email, download link)
    }
}
