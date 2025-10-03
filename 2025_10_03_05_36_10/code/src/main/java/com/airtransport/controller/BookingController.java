package com.airtransport.controller;

import com.airtransport.model.Booking;
import com.airtransport.model.Passenger;
import com.airtransport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    /**
     * Create booking API
     * @param requestBody Request body with flightId, passengerDetails, seatNumbers
     * @return Booking object
     */
    @PostMapping
    public Booking createBooking(@RequestBody Map<String, Object> requestBody) {
        Integer flightId = (Integer) requestBody.get("flightId");
        List<Map<String, String>> passengerDetailsMap = (List<Map<String, String>>) requestBody.get("passengerDetails");
        List<String> seatNumbers = (List<String>) requestBody.get("seats");
        List<Passenger> passengers = passengerDetailsMap.stream().map(pd -> {
            Passenger p = new Passenger();
            p.setName(pd.get("name"));
            p.setPassportNumber(pd.get("passportNumber"));
            return p;
        }).toList();
        return bookingService.createBooking(flightId, passengers, seatNumbers);
    }

    /**
     * Get booking confirmation API
     * @param bookingId Booking ID
     * @return Booking object
     */
    @GetMapping("/{bookingId}/confirmation")
    public Booking getBookingConfirmation(@PathVariable String bookingId) {
        return bookingService.confirmBooking(bookingId);
    }

    /**
     * Get booking details API
     * @param bookingId Booking ID
     * @return Booking object
     */
    @GetMapping("/{bookingId}")
    public Booking getBookingDetails(@PathVariable String bookingId) {
        return bookingService.getBookingDetails(bookingId);
    }
}
