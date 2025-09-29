package com.example.calculator.service;

import com.example.calculator.entity.KeyboardEvent;
import com.example.calculator.repository.KeyboardEventRepository;
import com.example.calculator.exception.InvalidKeyException;
import com.example.calculator.exception.KeyboardMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Service to listen and process keyboard events for calculator
 */
@Service
public class KeyboardListener {
    private static final Logger logger = LoggerFactory.getLogger(KeyboardListener.class);
    private static final List<String> VALID_KEYS = Arrays.asList("0","1","2","3","4","5","6","7","8","9","+","-","*","/","Enter","Escape");

    @Autowired
    private KeyboardEventRepository keyboardEventRepository;

    /**
     * Processes a keyboard input event
     * @param key String key pressed
     * @return status: "accepted" or "ignored"
     */
    public String onKeyPress(String key) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setTimestamp(LocalDateTime.now());
        String action = "";
        if (!VALID_KEYS.contains(key)) {
            event.setAction("ignored");
            keyboardEventRepository.save(event);
            throw new InvalidKeyException("Invalid key pressed");
        }
        switch (key) {
            case "+":
            case "-":
            case "*":
            case "/":
                action = "operation";
                break;
            case "Enter":
                action = "calculate";
                break;
            case "Escape":
                action = "reset";
                break;
            default:
                if (key.matches("[0-9]")) {
                    action = "input";
                } else {
                    throw new KeyboardMappingException("Unsupported operation key");
                }
        }
        event.setAction(action);
        keyboardEventRepository.save(event);
        logger.debug("Keyboard event processed: {}", event);
        return "accepted";
    }
}
