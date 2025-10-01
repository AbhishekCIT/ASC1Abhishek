package com.airtransport.controller;

import com.airtransport.model.Booking;
import com.airtransport.model.Payment;
import com.airtransport.service.BookingService;
import com.airtransport.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for handling booking and payment requests.
 */
@RestController
@RequestMapping("/api/flights")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    /**
     * Book a flight and process payment.
     * @param bookingRequest Booking request payload
     * @return Booking confirmation or error
     */
    @PostMapping("/book")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> bookFlight(@RequestBody BookingRequest bookingRequest) {
        try {
            Booking booking = bookingService.createBooking(
                bookingRequest.getFlightId(),
                bookingRequest.getUserId(),
                bookingRequest.getPassengerInfo()
            );
            Payment payment = paymentService.processPayment(booking.getBookingId(), bookingRequest.getPaymentDetails());
            if ("SUCCESS".equals(payment.getStatus())) {
                booking.setStatus("CONFIRMED");
                bookingService.updateBookingStatus(booking);
                return ResponseEntity.ok().body(new BookingResponse(booking.getBookingId(), booking.getStatus(), booking.getTicketNumber()));
            } else {
                booking.setStatus("PAYMENT_FAILED");
                bookingService.updateBookingStatus(booking);
                return ResponseEntity.badRequest().body(new ErrorResponse("Payment failed."));
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error."));
        }
    }

    // Booking request DTO
    public static class BookingRequest {
        private String flightId;
        private String userId;
        private Map<String, Object> passengerInfo;
        private Map<String, Object> paymentDetails;
        // Getters and setters
        public String getFlightId() { return flightId; }
        public void setFlightId(String flightId) { this.flightId = flightId; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public Map<String, Object> getPassengerInfo() { return passengerInfo; }
        public void setPassengerInfo(Map<String, Object> passengerInfo) { this.passengerInfo = passengerInfo; }
        public Map<String, Object> getPaymentDetails() { return paymentDetails; }
        public void setPaymentDetails(Map<String, Object> paymentDetails) { this.paymentDetails = paymentDetails; }
    }
    // Booking response DTO
    public static class BookingResponse {
        private String bookingId;
        private String status;
        private String ticketNumber;
        public BookingResponse(String bookingId, String status, String ticketNumber) {
            this.bookingId = bookingId;
            this.status = status;
            this.ticketNumber = ticketNumber;
        }
        public String getBookingId() { return bookingId; }
        public String getStatus() { return status; }
        public String getTicketNumber() { return ticketNumber; }
    }
    // Error response DTO
    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}
