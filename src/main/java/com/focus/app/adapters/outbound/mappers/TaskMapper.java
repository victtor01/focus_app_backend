package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaTaskEntity;
import com.focus.app.domain.models.Task;

public class TaskMapper {
    public static JpaTaskEntity toEntity(Task task) {
        if(task == null) return null;

        return JpaTaskEntity.builder()
            .id(task.getId())
            .name(task.getName())
            .color(task.getColor())
            .user(UserMapper.toEntity(task.getUser()))
            .description(task.getDescription())
            .build();
    }

    public static Task toDomain(JpaTaskEntity entity) {
        if(entity == null) return null;

        return new Task(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getColor(),
            null,
            UserMapper.toDomain(entity.getUser())
        );
    }
}
