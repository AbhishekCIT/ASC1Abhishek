package com.example.airbooking.util;

import com.example.airbooking.model.PassengerDetails;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for input validations.
 */
public class ValidationUtil {

    private static final List<String> SUPPORTED_DESTINATIONS = Arrays.asList("NYC", "LAX", "SFO", "ORD");

    public static void validateDate(String date) {
        try {
            LocalDate inputDate = LocalDate.parse(date);
            if (inputDate.isBefore(LocalDate.now())) {
                throw new ValidationException("Invalid date: must be in the future");
            }
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format");
        }
    }

    public static void validateDestination(String destination) {
        if (!SUPPORTED_DESTINATIONS.contains(destination)) {
            throw new ValidationException("Invalid destination");
        }
    }

    public static void validatePassengerDetails(PassengerDetails details) {
        if (details == null || details.getName() == null || details.getName().isEmpty() || details.getEmail() == null || details.getEmail().isEmpty()) {
            throw new ValidationException("Invalid passenger details");
        }
    }
}
