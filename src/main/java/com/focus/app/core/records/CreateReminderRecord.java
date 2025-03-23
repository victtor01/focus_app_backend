package com.focus.app.core.records;

import com.focus.app.core.enums.ReminderDay;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateReminderRecord(
    @NotNull
    UUID taskId,

    @NotNull
    LocalDateTime reminderTime,

    @NotNull
    List<ReminderDay> daysOfWeek
) {
}
