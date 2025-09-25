package com.example.scheduler.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TriggerReportResponse model
 */
public class TriggerReportResponseTest {
    private TriggerReportResponse response;
    private final String deliveryStatus = "success";
    private final LocalDateTime deliveryTime = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        response = new TriggerReportResponse();
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        TriggerReportResponse resp = new TriggerReportResponse(deliveryStatus, deliveryTime);
        assertEquals(deliveryStatus, resp.getDeliveryStatus());
        assertEquals(deliveryTime, resp.getDeliveryTime());
    }

    @Test
    @DisplayName("Test setters and getters")
    void testSettersAndGetters() {
        response.setDeliveryStatus(deliveryStatus);
        response.setDeliveryTime(deliveryTime);
        assertEquals(deliveryStatus, response.getDeliveryStatus());
        assertEquals(deliveryTime, response.getDeliveryTime());
    }

    @Test
    @DisplayName("Test default constructor initializes fields to null")
    void testDefaultConstructor() {
        TriggerReportResponse resp = new TriggerReportResponse();
        assertNull(resp.getDeliveryStatus());
        assertNull(resp.getDeliveryTime());
    }

    @Test
    @DisplayName("Test setting fields to null (edge case)")
    void testSetFieldsToNull() {
        response.setDeliveryStatus(null);
        response.setDeliveryTime(null);
        assertNull(response.getDeliveryStatus());
        assertNull(response.getDeliveryTime());
    }
}
