package com.example.airtransport.controller;

import com.example.airtransport.model.CancellationRequest;
import com.example.airtransport.model.CancellationResponse;
import com.example.airtransport.service.CancellationService;
import com.example.airtransport.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST controller for handling cancellation requests.
 */
@RestController
@RequestMapping("/api/bookings")
public class CancellationController {
    private static final Logger logger = LoggerFactory.getLogger(CancellationController.class);

    @Autowired
    private CancellationService cancellationService;
    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Exposes POST API endpoint for cancelling a booking.
     * @param request Cancellation request
     * @return Cancellation confirmation or error
     */
    @PostMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@Valid @RequestBody CancellationRequest request) {
        logger.info("Received cancellation request: {}", request);
        try {
            validationUtil.validateCancellationRequest(request);
            CancellationResponse response = cancellationService.cancelBooking(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid cancellation request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (com.example.airtransport.exception.IneligibleBookingException e) {
            logger.error("Ineligible booking: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (com.example.airtransport.exception.RefundCalculationException e) {
            logger.error("Refund calculation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (com.example.airtransport.exception.RefundFailedException e) {
            logger.error("Refund failed: {}", e.getMessage());
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Internal server error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
