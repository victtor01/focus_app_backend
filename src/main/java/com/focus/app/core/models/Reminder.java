package com.focus.app.core.models;

import com.focus.app.core.enums.ReminderDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "reminders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reminder {
    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    @Column(name = "days_of_week")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ReminderDay> daysOfWeek;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
