package com.focus.app.application.commands;

import java.util.List;
import java.util.UUID;

public record CreateTaskCommand(
    String name,
    String description,
    String color,
    List<UUID> categoriesIds
){
}
