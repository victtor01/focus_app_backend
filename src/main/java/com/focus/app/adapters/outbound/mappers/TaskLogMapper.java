package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaTaskLogEntity;
import com.focus.app.domain.models.TaskLog;

public class TaskLogMapper {
    public static TaskLog toDomain(JpaTaskLogEntity taskLogEntity) {
        return new TaskLog(
            taskLogEntity.getId(),
            taskLogEntity.getDay(),
            taskLogEntity.getHour(),
            UserMapper.toDomain(taskLogEntity.getUser()),
            TaskMapper.toDomain(taskLogEntity.getTask())
        );
    }

    public static JpaTaskLogEntity toEntity(TaskLog taskLog) {
        return JpaTaskLogEntity.builder()
            .id(taskLog.getId())
            .task(TaskMapper.toEntity(taskLog.getTask()))
            .user(UserMapper.toEntity(taskLog.getUser()))
            .day(taskLog.getDay())
            .hour(taskLog.getHour())
            .build();
    }
}
