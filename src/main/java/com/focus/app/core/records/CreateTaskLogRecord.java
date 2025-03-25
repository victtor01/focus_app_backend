package com.focus.app.core.records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTaskLogRecord(
    @NotNull
    LocalDate day,

    @NotNull
    UUID taskId
) {
}
