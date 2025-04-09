package com.focus.app.adapters.inbound.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateTaskLogRequest(
    @NotNull
    LocalDate day,

    @NotNull
    UUID taskId,

    @NotNull
    UUID reminderId,

    LocalTime hour,

    long duration
) {
}
