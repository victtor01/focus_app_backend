package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaTaskEntity;
import com.focus.app.domain.models.task.Task;

public class TaskMapper {
    public static JpaTaskEntity toEntity(Task task) {
        if (task == null) return null;

        return JpaTaskEntity.builder()
            .id(task.getId())
            .name(task.getName())
            .color(task.getColor())
            .user(UserMapper.toEntity(task.getUser()))
            .categories(task.getCategories().stream().map(TaskCategoryMapper::toEntity).toList())
            .reminders(task.getReminders().stream().map(ReminderMapper::toEntity).toList())
            .description(task.getDescription())
            .build();
    }

    public static Task toDomain(JpaTaskEntity entity) {
        if (entity == null) return null;

        return Task.builder()
            .user(UserMapper.toDomain(entity.getUser()))
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .categories(entity.getCategories().stream().map(TaskCategoryMapper::toDomain).toList())
            .reminders(entity.getReminders().stream().map(ReminderMapper::toSimple).toList())
            .color(entity.getColor())
            .build();
    }
}
