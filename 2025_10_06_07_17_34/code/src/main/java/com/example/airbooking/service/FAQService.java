package com.example.airbooking.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for FAQs and help center articles.
 */
@Service
public class FAQService {
    /**
     * Returns a list of FAQs (static for demo).
     * @return List of FAQs
     */
    public List<Map<String, String>> getFaqs() {
        List<Map<String, String>> faqs = new ArrayList<>();
        Map<String, String> faq1 = new HashMap<>();
        faq1.put("faqId", "F1");
        faq1.put("question", "How to book?");
        faq1.put("answer", "Go to the booking page and follow the instructions.");
        faqs.add(faq1);
        Map<String, String> faq2 = new HashMap<>();
        faq2.put("faqId", "F2");
        faq2.put("question", "How to track baggage?");
        faq2.put("answer", "Use the baggage tracking feature in your account.");
        faqs.add(faq2);
        return faqs;
    }
}