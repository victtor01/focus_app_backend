package com.focus.app.application.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CreateTaskLogCommand(
    LocalDate day,
    UUID taskId,
    LocalTime hour
) {
}
