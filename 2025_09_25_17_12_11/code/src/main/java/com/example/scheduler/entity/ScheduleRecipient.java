package com.example.scheduler.entity;

import javax.persistence.*;

/**
 * Entity representing a recipient of a schedule.
 */
@Entity
@Table(name = "schedule_recipients")
public class ScheduleRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(nullable = false)
    private String email;

    public ScheduleRecipient() {}

    public ScheduleRecipient(Schedule schedule, String email) {
        this.schedule = schedule;
        this.email = email;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
