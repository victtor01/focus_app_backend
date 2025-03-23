package com.focus.app.core.records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRecord(
    @NotEmpty(message = "field name not found!")
    @NotNull(message = "name of task is required")
    String name,

    String description,
    String color
) {
}
