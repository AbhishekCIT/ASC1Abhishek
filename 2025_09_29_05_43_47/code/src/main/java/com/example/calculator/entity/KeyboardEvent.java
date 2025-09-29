package com.example.calculator.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing keyboard event logs
 */
@Entity
@Table(name = "keyboard_event")
public class KeyboardEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String key;

    @Column
    private String action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public KeyboardEvent() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
