package com.asc.airbooking.service;

import com.asc.airbooking.entity.Flight;
import com.asc.airbooking.entity.Booking;
import com.asc.airbooking.model.BookFlightRequest;
import com.asc.airbooking.model.BookFlightResponse;
import com.asc.airbooking.model.FlightSearchRequest;
import com.asc.airbooking.model.FlightSearchResponse;
import com.asc.airbooking.repository.FlightRepository;
import com.asc.airbooking.repository.BookingRepository;
import com.asc.airbooking.service.PaymentService;
import com.asc.airbooking.util.AirportCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for flight operations: search and booking.
 */
@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;
    private final AirportCodeUtil airportCodeUtil;
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    /**
     * Finds flights based on origin, destination, and date.
     * @param request FlightSearchRequest
     * @return FlightSearchResponse
     */
    @Cacheable("flightSearch")
    public FlightSearchResponse findFlights(FlightSearchRequest request) {
        logger.debug("Searching flights: {}", request);
        // Validate airport codes
        if (!airportCodeUtil.isValidAirportCode(request.getOrigin())) {
            logger.warn("Invalid origin airport code: {}", request.getOrigin());
            throw new IllegalArgumentException("Invalid origin airport code.");
        }
        if (!airportCodeUtil.isValidAirportCode(request.getDestination())) {
            logger.warn("Invalid destination airport code: {}", request.getDestination());
            throw new IllegalArgumentException("Invalid destination airport code.");
        }
        // Validate date
        if (request.getDate().isBefore(LocalDate.now())) {
            logger.warn("Travel date must be in the future: {}", request.getDate());
            throw new IllegalArgumentException("Travel date must be in the future.");
        }
        LocalDateTime start = request.getDate().atStartOfDay();
        LocalDateTime end = request.getDate().atTime(23, 59, 59);
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
            request.getOrigin(), request.getDestination(), start, end);
        if (flights.isEmpty()) {
            logger.warn("No flights found for criteria: {}", request);
            throw new RuntimeException("No flights found for the given criteria.");
        }
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(flights.stream().map(f -> {
            FlightSearchResponse.FlightInfo info = new FlightSearchResponse.FlightInfo();
            info.setFlightId(f.getFlightId());
            info.setAirline(f.getAirline());
            info.setTime(f.getDepartureTime().toString());
            info.setPrice(f.getPrice());
            return info;
        }).collect(Collectors.toList()));
        logger.info("Flights found: {}", response.getFlights().size());
        return response;
    }

    /**
     * Books a flight for a passenger, processes payment, and saves booking.
     * @param request BookFlightRequest
     * @return BookFlightResponse
     */
    @Transactional
    public BookFlightResponse bookFlight(BookFlightRequest request) {
        logger.debug("Booking flight: {}", request);
        Flight flight = flightRepository.findById(request.getFlightId())
            .orElseThrow(() -> new RuntimeException("Selected flight is no longer available."));
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(flight.getPrice(), request.getPayment().getToken());
        if (!paymentSuccess) {
            logger.error("Payment failed for flight: {}", request.getFlightId());
            throw new RuntimeException("Payment could not be processed.");
        }
        // Save booking
        String confirmationId = UUID.randomUUID().toString();
        Booking booking = new Booking();
        booking.setConfirmationId(confirmationId);
        booking.setFlight(flight);
        booking.setPassengerName(request.getPassenger().getName());
        booking.setPassengerEmail(request.getPassenger().getEmail());
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus("success");
        bookingRepository.save(booking);
        logger.info("Booking confirmed: {}", confirmationId);
        BookFlightResponse response = new BookFlightResponse();
        response.setConfirmationId(confirmationId);
        response.setStatus("confirmed");
        return response;
    }
}
