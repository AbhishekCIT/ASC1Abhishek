package com.airtransport.booking.model;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentStatus;
    private String transactionId;
}
