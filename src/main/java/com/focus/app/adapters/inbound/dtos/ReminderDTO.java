package com.focus.app.adapters.inbound.dtos;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.models.Reminder;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderDTO {
    private UUID id;
    private LocalDateTime reminderTime;
    private List<ReminderDay> daysOfWeek;

    public ReminderDTO(Reminder reminder) {
        this.reminderTime = reminder.getReminderTime();
        this.daysOfWeek = reminder.getDaysOfWeek();
        this.id = reminder.getId();
    }

    public static ReminderDTO toEntity(Reminder reminder) {
        return ReminderDTO.builder()
            .id(reminder.getId())
            .reminderTime(reminder.getReminderTime())
            .daysOfWeek(reminder.getDaysOfWeek())
            .build();
    }

}
