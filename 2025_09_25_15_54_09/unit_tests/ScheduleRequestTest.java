package com.example.scheduler.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ScheduleRequest POJO.
 */
public class ScheduleRequestTest {

    /**
     * Test getters and setters for all fields.
     */
    @Test
    void testGettersAndSetters() {
        ScheduleRequest req = new ScheduleRequest();
        req.setReportId("report-1");
        req.setFrequency("DAILY");
        req.setTime("08:00");
        List<String> methods = Arrays.asList("EMAIL", "DOWNLOAD");
        req.setDeliveryMethods(methods);
        List<String> recipients = Collections.singletonList("user@domain.com");
        req.setRecipients(recipients);

        assertEquals("report-1", req.getReportId());
        assertEquals("DAILY", req.getFrequency());
        assertEquals("08:00", req.getTime());
        assertEquals(methods, req.getDeliveryMethods());
        assertEquals(recipients, req.getRecipients());
    }

    /**
     * Test setting null and empty lists (edge cases).
     */
    @Test
    void testNullAndEmptyLists() {
        ScheduleRequest req = new ScheduleRequest();
        req.setDeliveryMethods(null);
        req.setRecipients(null);
        assertNull(req.getDeliveryMethods());
        assertNull(req.getRecipients());
        req.setDeliveryMethods(Collections.emptyList());
        req.setRecipients(Collections.emptyList());
        assertTrue(req.getDeliveryMethods().isEmpty());
        assertTrue(req.getRecipients().isEmpty());
    }
}
