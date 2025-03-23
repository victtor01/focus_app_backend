package com.focus.app.core.dtos;

import com.focus.app.core.enums.ReminderDay;
import com.focus.app.core.models.Reminder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ReminderDTO {
    private UUID id;
    private LocalDateTime reminderTime;
    private List<ReminderDay> daysOfWeek;

    public ReminderDTO(Reminder reminder) {
        this.reminderTime = reminder.getReminderTime();
        this.daysOfWeek = reminder.getDaysOfWeek();
        this.id = reminder.getId();
    }
}
