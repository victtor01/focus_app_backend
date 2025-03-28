package com.focus.app.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.focus.app.domain.models.TaskLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskLogDTO {
    private UUID id;
    private LocalDate day;
    private TaskDTO task;
    private LocalTime hour;

    public static TaskLogDTO toEntity(TaskLog taskLog) {
        if (taskLog == null) return null;

        return TaskLogDTO.builder()
            .day(taskLog.getDay())
            .id(taskLog.getId())
            .task(TaskDTO.toEntity(taskLog.getTask()))
            .hour(taskLog.getHour())
            .build();
    }
}

