package com.focus.app.adapters.inbound.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateTaskCategoryRecord (
    @NotEmpty @NotNull String name,
    @NotNull String color
) {
}
