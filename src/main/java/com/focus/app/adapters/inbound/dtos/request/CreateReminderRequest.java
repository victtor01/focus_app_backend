package com.focus.app.adapters.inbound.dtos.request;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.enums.ReminderType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateReminderRequest(
    @NotNull(message = "reminder task is required")
    UUID taskId,

    @NotNull(message = "reminder type is required")
    ReminderType reminderType,

    List<LocalDate> customReminderDates,

    List<LocalDate> reminderDaysOfWeek
){
}
