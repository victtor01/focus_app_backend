package com.focus.app.adapters.inbound.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateTaskRequest(
    @NotEmpty(message = "field name not found!")
    @NotNull(message = "name of task is required")
    String name,

    String description,

    String color,

    List<UUID> categoriesIds
) {
}
