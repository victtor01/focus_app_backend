package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaTaskLogEntity;
import com.focus.app.domain.models.TaskLog;

public class TaskLogMapper {
    public static TaskLog toDomain(JpaTaskLogEntity taskLogEntity) {
        if(taskLogEntity == null) return null;

        return new TaskLog(
            taskLogEntity.getId(),
            taskLogEntity.getDay(),
            taskLogEntity.getHour(),
            UserMapper.toDomain(taskLogEntity.getUser()),
            TaskMapper.toDomain(taskLogEntity.getTask()),
            ReminderMapper.toSimple(taskLogEntity.getReminder()),
            taskLogEntity.getDuration()
        );
    }

    public static JpaTaskLogEntity toEntity(TaskLog taskLog) {
        if(taskLog == null) return null;
        return JpaTaskLogEntity.builder()
            .id(taskLog.getId())
            .task(TaskMapper.toEntity(taskLog.getTask()))
            .user(UserMapper.toEntity(taskLog.getUser()))
            .task(TaskMapper.toEntity(taskLog.getTask()))
            .reminder(ReminderMapper.toEntity(taskLog.getReminder()))
            .day(taskLog.getDay())
            .hour(taskLog.getHour())
            .duration(taskLog.getDuration())
            .build();
    }
}
