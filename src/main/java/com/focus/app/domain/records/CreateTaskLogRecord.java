package com.focus.app.domain.records;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateTaskLogRecord(
    @NotNull
    LocalDate day,

    @NotNull
    UUID taskId,

    LocalTime hour
) {
}
