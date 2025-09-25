package com.example.scheduledreporting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for scheduled report operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledReportResponse {
    private Integer id;
    private String status;
    private String message;
}
