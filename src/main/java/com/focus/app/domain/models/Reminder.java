package com.focus.app.domain.models;

import com.focus.app.domain.enums.ReminderDay;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Reminder {
    private UUID id;

    private LocalDateTime reminderTime;

    private Task task;

    private List<ReminderDay> daysOfWeek;

    private User user;

    public Reminder() {}
}
