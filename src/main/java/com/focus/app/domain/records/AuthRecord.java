package com.focus.app.domain.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthRecord(
    @NotBlank(message = "email is required")
    @NotNull(message = "this email not valid")
    @Email(message = "this email is format invalid")
    String email,

    @NotEmpty(message = "password is not empty")
    @NotNull(message = "password is required")
    String password
    ) {
}
