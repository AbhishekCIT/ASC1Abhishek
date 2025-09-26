package com.airtransport.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * Utility for generating unique booking references.
 */
@Component
public class BookingRefUtil {
    /**
     * Generates a unique booking reference.
     * @return Booking reference
     */
    public String generateBookingRef() {
        return "BR" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8).toUpperCase();
    }
}
