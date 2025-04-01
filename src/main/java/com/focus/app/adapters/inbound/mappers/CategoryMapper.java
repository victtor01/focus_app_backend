package com.focus.app.adapters.inbound.mappers;

import com.focus.app.adapters.inbound.dtos.request.CreateTaskCategoryRecord;
import com.focus.app.adapters.inbound.dtos.response.TaskCategoryResponse;
import com.focus.app.adapters.inbound.dtos.response.TaskResponse;
import com.focus.app.adapters.outbound.entities.JpaTaskCategoryEntity;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.application.commands.CreateTaskCategoryCommand;
import com.focus.app.domain.models.taskCategory.TaskCategory;

public class CategoryMapper {
    public static TaskCategoryResponse toResponse(TaskCategory taskCategory) {
        return new TaskCategoryResponse(
            taskCategory.getId(),
            taskCategory.getName(),
            taskCategory.getColor()
        );
    }

    public static TaskCategory toDomain(JpaTaskCategoryEntity taskCategory) {
        return TaskCategory.builder()
            .id(taskCategory.getId())
            .name(taskCategory.getName())
            .user(UserMapper.toDomain(taskCategory.getUser()))
            .color(taskCategory.getColor())
            .build();
    }
}
