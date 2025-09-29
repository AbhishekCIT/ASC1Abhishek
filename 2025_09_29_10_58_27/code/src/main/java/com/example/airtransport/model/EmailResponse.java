package com.example.airtransport.model;

import lombok.Data;

/**
 * Response model for sending confirmation email.
 */
@Data
public class EmailResponse {
    private String status;
    private String eTicketUrl;
}
