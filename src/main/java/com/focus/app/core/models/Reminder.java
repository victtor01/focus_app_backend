package com.focus.app.core.models;

import com.focus.app.core.enums.ReminderDay;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "reminders")
public class Reminder {
    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private LocalTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ReminderDay> daysOfWeek;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
