package com.example.calculator.util;

import com.example.calculator.exception.InvalidKeyException;
import org.springframework.stereotype.Component;

/**
 * Service to map keyboard events to calculator actions.
 * Note: In a typical web application, this would be handled on the frontend (e.g., React).
 */
@Component
public class KeyboardEventHandlerService {
    /**
     * Handles a keyboard event and returns the mapped action.
     * Throws InvalidKeyException for unsupported keys.
     * @param key The key pressed
     * @return The mapped action (String)
     */
    public String handleKey(String key) {
        // Acceptable keys: 0-9, ., +, -, *, /, Enter, Esc
        if (key == null) {
            throw new InvalidKeyException("Key cannot be null.");
        }
        switch (key) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
            case ".":
            case "+": case "-": case "*": case "/":
            case "Enter": case "Esc":
                return key;
            default:
                throw new InvalidKeyException("Invalid key pressed.");
        }
    }
}
