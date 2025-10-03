package com.airtransport.controller;

import com.airtransport.dto.BookingRequest;
import com.airtransport.dto.BookingResponse;
import com.airtransport.exception.InvalidPassengerException;
import com.airtransport.exception.PaymentFailedException;
import com.airtransport.exception.SeatUnavailableException;
import com.airtransport.exception.ValidationException;
import com.airtransport.service.BookingService;
import com.airtransport.util.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller to handle air transport booking and payment API requests.
 */
@RestController
@RequestMapping("/api/air")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;
    @Autowired
    private ErrorHandler errorHandler;

    /**
     * POST endpoint to book a flight and process payment.
     * @param request BookingRequest JSON payload
     * @return BookingResponse or error
     */
    @PostMapping("/book")
    public ResponseEntity<?> bookFlight(@Valid @RequestBody BookingRequest request) {
        logger.debug("Received booking request: {}", request);
        try {
            BookingResponse response = bookingService.createBooking(request);
            logger.info("Booking successful. Reference: {}", response.getBookingReference());
            return ResponseEntity.ok(response);
        } catch (InvalidPassengerException | PaymentFailedException | SeatUnavailableException | ValidationException e) {
            logger.error("Booking validation error: {}", e.getMessage());
            return errorHandler.handleError(e);
        } catch (Exception e) {
            logger.error("Unexpected booking error: {}", e.getMessage(), e);
            return errorHandler.handleError(new ValidationException("Internal server error."));
        }
    }
}
