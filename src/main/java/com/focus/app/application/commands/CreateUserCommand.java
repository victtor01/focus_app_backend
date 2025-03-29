package com.focus.app.application.commands;

public record CreateUserCommand(
    String username,
    String password,
    String email
) {
}
