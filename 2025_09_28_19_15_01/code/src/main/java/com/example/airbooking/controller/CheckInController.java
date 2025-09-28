package com.example.airbooking.controller;

import com.example.airbooking.model.*;
import com.example.airbooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller for check-in, seat map, and boarding pass APIs.
 */
@RestController
@RequestMapping("/api")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;
    @Autowired
    private SeatSelectionService seatSelectionService;
    @Autowired
    private BoardingPassService boardingPassService;

    /**
     * Perform online check-in for a booking.
     * @param request CheckInRequest
     * @return CheckInResponse
     */
    @PostMapping("/checkin")
    public ResponseEntity<CheckInResponse> checkIn(@Valid @RequestBody CheckInRequest request) {
        CheckInResponse response = checkInService.checkIn(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get seat map for a flight.
     * @param flightId Flight ID
     * @return SeatMapResponse
     */
    @GetMapping("/flights/{flightId}/seatmap")
    public ResponseEntity<SeatMapResponse> getSeatMap(@PathVariable String flightId) {
        SeatMapResponse response = seatSelectionService.getSeatMap(flightId);
        return ResponseEntity.ok(response);
    }

    /**
     * Get boarding pass for a check-in.
     * @param checkInId Check-in ID
     * @return BoardingPassResponse
     */
    @GetMapping("/checkin/{checkInId}/boardingpass")
    public ResponseEntity<BoardingPassResponse> getBoardingPass(@PathVariable String checkInId) {
        BoardingPassResponse response = boardingPassService.generateBoardingPass(checkInId);
        return ResponseEntity.ok(response);
    }
}
