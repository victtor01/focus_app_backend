package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaTaskCategoryEntity;
import com.focus.app.domain.models.taskCategory.TaskCategory;

public class TaskCategoryMapper {
    public static TaskCategory toDomain(JpaTaskCategoryEntity taskCategory) {
        if (taskCategory == null) return null;

        return TaskCategory.builder()
            .id(taskCategory.getId())
            .name(taskCategory.getName())
            .color(taskCategory.getColor())
            .user(UserMapper.toDomain(taskCategory.getUser()))
            .build();
    }

    public static JpaTaskCategoryEntity toEntity(TaskCategory taskCategory) {
        if (taskCategory == null) return null;

        return JpaTaskCategoryEntity.builder()
            .id(taskCategory.getId())
            .name(taskCategory.getName())
            .color(taskCategory.getColor())
            .user(UserMapper.toEntity(taskCategory.getUser()))
            .build();
    }

}
