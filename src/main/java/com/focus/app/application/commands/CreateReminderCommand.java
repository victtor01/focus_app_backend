package com.focus.app.application.commands;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.enums.ReminderType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateReminderCommand(
    UUID taskId,
    List<LocalDate> customRemindersDates,
    List<LocalDate> daysOfWeek,
    ReminderType reminderType
) {
}
