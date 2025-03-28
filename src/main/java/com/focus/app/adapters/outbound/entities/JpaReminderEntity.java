package com.focus.app.adapters.outbound.entities;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
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
public class JpaReminderEntity {
    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JpaTaskEntity task;

    @Column(name = "days_of_week")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ReminderDay> daysOfWeek;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUserEntity user;
}
