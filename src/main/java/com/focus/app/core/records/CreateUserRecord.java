package com.focus.app.core.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRecord(
    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "username length is invalid")
    String username,

    @Size(min = 6, max = 50, message = "length of password is invalid")
    @NotBlank(message = "password is required")
    String password,

    @Size(min = 6, max = 50, message = "length of email is invalid")
    @NotBlank(message = "email is required")
    String email
) {
}
