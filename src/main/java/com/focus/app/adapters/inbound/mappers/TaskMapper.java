package com.focus.app.adapters.inbound.mappers;


import com.focus.app.adapters.inbound.dtos.ReminderDTO;
import com.focus.app.adapters.inbound.dtos.TaskDTO;
import com.focus.app.domain.models.Task;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        return TaskDTO.builder()
            .id(task.getId())
            .name(task.getName())
            .reminders(null)
            .description(task.getDescription())
            .build();
    }
}
