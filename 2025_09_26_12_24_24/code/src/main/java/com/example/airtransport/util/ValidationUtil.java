package com.example.airtransport.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility for validating input fields.
 */
@Component
public class ValidationUtil {
    private static final Pattern IATA_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");

    public void validateAirportCode(String code, String fieldName) {
        if (code == null || !IATA_CODE_PATTERN.matcher(code).matches()) {
            throw new IllegalArgumentException("Invalid " + fieldName + " airport code");
        }
    }

    public void validateTravelDates(String departureDate, String returnDate) {
        LocalDate dep = LocalDate.parse(departureDate);
        if (dep.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid departure date");
        }
        if (returnDate != null && !returnDate.isEmpty()) {
            LocalDate ret = LocalDate.parse(returnDate);
            if (ret.isBefore(dep)) {
                throw new IllegalArgumentException("Invalid return date");
            }
        }
    }

    public void validatePassengerDetails(List<?> passengerDetails) {
        if (passengerDetails == null || passengerDetails.isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger details");
        }
    }

    public void validatePaymentInfo(Object paymentInfo) {
        // For demonstration, assume paymentInfo is not null
        if (paymentInfo == null) {
            throw new IllegalArgumentException("Invalid payment information");
        }
        // Additional PCI DSS checks would be implemented here
    }
}
