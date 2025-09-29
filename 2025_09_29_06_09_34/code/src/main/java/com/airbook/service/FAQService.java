package com.airbook.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

/**
 * Service for managing FAQs and help center articles
 */
@Service
public class FAQService {
    /**
     * Fetch FAQs (mock implementation)
     */
    public List<String> fetchFaqs() {
        return Arrays.asList(
            "How to cancel a booking? - Go to My Bookings and select Cancel.",
            "How to request a refund? - Submit a support request with subject 'Refund'.",
            "How to change flight date? - Modify booking from My Bookings."
        );
    }
}
