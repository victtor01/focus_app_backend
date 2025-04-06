package com.focus.app.adapters.inbound.mappers;

import com.focus.app.adapters.inbound.dtos.response.TaskResponse;
import com.focus.app.domain.models.task.Task;

public class TaskMapper {
    public static TaskResponse toResponse(Task task) {
        if(task == null) return null;
        return TaskResponse.builder()
            .id(task.getId())
            .name(task.getName())
            .reminders(task.getReminders().stream().map(ReminderMapper::toResponse).toList())
            .categories(task.getCategories().stream().map(CategoryMapper::toResponse).toList())
            .description(task.getDescription())
            .build();
    }

    public static TaskResponse toSimpleResponse(Task task) {
        if (task == null) return null;

        return TaskResponse.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .build();
    }
}
