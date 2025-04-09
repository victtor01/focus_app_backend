package com.focus.app.adapters.inbound.mappers;

import com.focus.app.adapters.inbound.dtos.response.TaskLogResponse;
import com.focus.app.domain.models.TaskLog;

public class TaskLogMapper {
    public static TaskLogResponse toResponse(TaskLog taskLog) {
        if (taskLog == null) return null;

        return TaskLogResponse.builder()
            .id(taskLog.getId())
            .reminder(ReminderMapper.toSimpleResponse(taskLog.getReminder()))
            .task(TaskMapper.toSimpleResponse(taskLog.getTask()))
            .day(taskLog.getDay())
            .duration(taskLog.getDuration())
            .hour(taskLog.getHour())
            .build();
    }

    public static TaskLogResponse toSimpleResponse(TaskLog taskLog) {
        if (taskLog == null) return null;

        return TaskLogResponse.builder()
            .id(taskLog.getId())
            .day(taskLog.getDay())
            .duration(taskLog.getDuration())
            .hour(taskLog.getHour())
            .build();
    }
}
