package com.airtransport.util;

import com.airtransport.exception.InvalidLocationException;
import com.airtransport.exception.PastDateException;
import com.airtransport.exception.ProviderAPIException;
import com.airtransport.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility to handle and format API errors.
 */
@Component
public class ErrorHandler {
    public ResponseEntity<Map<String, String>> handleError(Exception e) {
        Map<String, String> error = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (e instanceof InvalidLocationException || e instanceof ValidationException) {
            error.put("error", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof PastDateException) {
            error.put("error", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof ProviderAPIException) {
            error.put("error", "Flight provider service unavailable. Please try again later.");
            status = HttpStatus.SERVICE_UNAVAILABLE;
        } else {
            error.put("error", "Internal server error.");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(error, status);
    }
}
