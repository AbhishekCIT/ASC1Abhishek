package com.airtransport.controller;

import com.airtransport.model.Payment;
import com.airtransport.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /**
     * Process payment API
     * @param requestBody Request body with bookingId and paymentDetails
     * @return Payment object
     */
    @PostMapping
    public Payment processPayment(@RequestBody Map<String, Object> requestBody) {
        String bookingId = (String) requestBody.get("bookingId");
        Map<String, String> paymentDetails = (Map<String, String>) requestBody.get("paymentDetails");
        return paymentService.processPayment(bookingId, paymentDetails);
    }
}
