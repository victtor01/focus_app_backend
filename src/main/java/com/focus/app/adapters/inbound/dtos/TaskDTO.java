package com.focus.app.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.focus.app.domain.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDTO {
    private UUID id;
    private String name;
    private String description;
    private List<ReminderDTO> reminders;

    public static TaskDTO toEntity(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .build();
    }
}
