package com.focus.app.application.commands;

public record CreateTaskCategoryCommand(
    String name,
    String color
) {
}
