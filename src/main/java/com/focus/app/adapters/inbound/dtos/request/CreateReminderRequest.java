package com.focus.app.adapters.inbound.dtos.request;

import com.focus.app.domain.enums.ReminderDay;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateReminderRequest(
    @NotNull
    UUID taskId,

    @NotNull
    LocalDateTime reminderTime,

    @NotNull
    List<ReminderDay> daysOfWeek
){
}
