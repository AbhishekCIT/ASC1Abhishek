package com.example.calculator.controller;

import com.example.calculator.service.ShortcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Keyboard Shortcut feature logging
 */
@RestController
@RequestMapping("/api/shortcut")
public class ShortcutController {

    @Autowired
    private ShortcutService shortcutService;

    /**
     * Exposes the /log endpoint to log usage of keyboard shortcuts
     * @return Success message
     */
    @PostMapping("/log")
    public ResponseEntity<?> logShortcutUsage() {
        shortcutService.logShortcutUsage();
        return ResponseEntity.ok("Keyboard shortcut usage logged successfully.");
    }
}
