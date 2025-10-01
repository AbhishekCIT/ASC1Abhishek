package com.airtransport.booking.util;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Utility class for common validation logic.
 */
@Component
public class ValidationUtil {
    private static final Pattern AIRPORT_CODE_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^[0-9]{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^[0-9]{3,4}$");

    /**
     * Validates IATA airport code (3 uppercase letters).
     */
    public boolean isValidAirportCode(String code) {
        return code != null && AIRPORT_CODE_PATTERN.matcher(code).matches();
    }

    /**
     * Validates that the travel date is in the future.
     */
    public boolean isFutureDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    /**
     * Validates card number (simple check, PCI DSS compliance required in real systems).
     */
    public boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && CARD_NUMBER_PATTERN.matcher(cardNumber).matches();
    }

    /**
     * Validates CVV.
     */
    public boolean isValidCVV(String cvv) {
        return cvv != null && CVV_PATTERN.matcher(cvv).matches();
    }
}
