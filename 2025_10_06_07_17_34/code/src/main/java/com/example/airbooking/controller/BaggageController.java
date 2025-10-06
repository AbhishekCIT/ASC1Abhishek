package com.example.airbooking.controller;

import com.example.airbooking.model.Baggage;
import com.example.airbooking.service.BaggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for baggage management operations.
 */
@RestController
@RequestMapping("/api/baggage")
public class BaggageController {
    @Autowired
    private BaggageService baggageService;

    /**
     * View baggage info for a booking.
     * @param bookingId Booking ID
     * @return List of Baggage
     */
    @GetMapping("/info")
    public List<Baggage> viewBaggageInfo(@RequestParam String bookingId) {
        return baggageService.viewBaggageInfo(bookingId);
    }

    /**
     * Add or update baggage for a booking.
     * @param updateRequest Request body containing bookingId and bags
     * @return List of updated Baggage
     */
    @PostMapping("/update")
    public List<Baggage> updateBaggage(@RequestBody Map<String, Object> updateRequest) {
        String bookingId = (String) updateRequest.get("bookingId");
        List<Baggage> bags = (List<Baggage>) updateRequest.get("bags");
        return baggageService.updateBaggage(bookingId, bags);
    }

    /**
     * Track baggage by baggage ID.
     * @param baggageId Baggage ID
     * @return Baggage
     */
    @GetMapping("/track")
    public Baggage trackBaggage(@RequestParam String baggageId) {
        return baggageService.trackBaggage(baggageId);
    }
}