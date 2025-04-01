package com.focus.app.adapters.outbound.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.enums.ReminderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
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

    @Column(name = "custom_reminders_dates")
    private List<LocalDate> customReminderDates;

    @Column(name = "days_of_week")
    private List<LocalDate> daysOfWeek;

    @Column(name = "reminder type")
    @Enumerated(EnumType.STRING)
    private ReminderType reminderType;

    @Column(name = "repeat")
    private boolean isRecurring;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JpaTaskEntity task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUserEntity user;
}
