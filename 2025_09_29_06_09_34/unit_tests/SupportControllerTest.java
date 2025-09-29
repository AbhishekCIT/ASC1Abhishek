package com.airbook.controller;

import com.airbook.model.SupportRequest;
import com.airbook.model.SupportHistory;
import com.airbook.service.SupportService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SupportController
 */
class SupportControllerTest {

    @Mock
    private SupportService supportService;

    @InjectMocks
    private SupportController supportController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    /**
     * Test getFaqs returns FAQs
     */
    @Test
    void testGetFaqs_ReturnsFaqs() {
        List<String> faqs = Arrays.asList("FAQ1", "FAQ2");
        when(supportService.getFaqs()).thenReturn(faqs);

        ResponseEntity<List<String>> response = supportController.getFaqs();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(faqs, response.getBody());
    }

    /**
     * Test createSupportRequest returns created request
     */
    @Test
    void testCreateSupportRequest_ValidRequest_ReturnsCreatedRequest() {
        SupportRequest request = new SupportRequest();
        SupportRequest created = new SupportRequest();
        when(supportService.createSupportRequest(request)).thenReturn(created);

        ResponseEntity<SupportRequest> response = supportController.createSupportRequest(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(created, response.getBody());
    }

    /**
     * Test createSupportRequest with null request (error scenario)
     */
    @Test
    void testCreateSupportRequest_NullRequest_ReturnsNull() {
        when(supportService.createSupportRequest(null)).thenReturn(null);

        ResponseEntity<SupportRequest> response = supportController.createSupportRequest(null);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    /**
     * Test getSupportRequests returns requests for valid user
     */
    @Test
    void testGetSupportRequests_ValidUser_ReturnsRequests() {
        String userId = "user123";
        List<SupportRequest> requests = Arrays.asList(new SupportRequest(), new SupportRequest());
        when(supportService.getSupportRequests(userId)).thenReturn(requests);

        ResponseEntity<List<SupportRequest>> response = supportController.getSupportRequests(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(requests, response.getBody());
    }

    /**
     * Test getSupportRequests with empty userId (edge case)
     */
    @Test
    void testGetSupportRequests_EmptyUserId_ReturnsEmptyList() {
        String userId = "";
        when(supportService.getSupportRequests(userId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<SupportRequest>> response = supportController.getSupportRequests(userId);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    /**
     * Test getSupportRequestStatus returns history for valid requestId
     */
    @Test
    void testGetSupportRequestStatus_ValidRequestId_ReturnsHistory() {
        String requestId = "req123";
        SupportHistory history = new SupportHistory();
        when(supportService.getSupportRequestStatus(requestId)).thenReturn(history);

        ResponseEntity<SupportHistory> response = supportController.getSupportRequestStatus(requestId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(history, response.getBody());
    }

    /**
     * Test getSupportRequestStatus with invalid requestId (error scenario)
     */
    @Test
    void testGetSupportRequestStatus_InvalidRequestId_ReturnsNull() {
        String requestId = "invalid";
        when(supportService.getSupportRequestStatus(requestId)).thenReturn(null);

        ResponseEntity<SupportHistory> response = supportController.getSupportRequestStatus(requestId);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
