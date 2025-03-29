package com.focus.app.application.commands;

import com.focus.app.domain.enums.ReminderDay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateReminderCommand(
    UUID taskId,
    LocalDateTime reminderTime,
    List<ReminderDay> daysOfWeek
) {
}
