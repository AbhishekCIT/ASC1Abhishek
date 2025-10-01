package com.airtransport.booking.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotBlank(message = "Booking reference is required")
    private String bookingRef;

    @NotNull(message = "Payment info is required")
    private BookingRequest.PaymentInfo paymentInfo;
}
