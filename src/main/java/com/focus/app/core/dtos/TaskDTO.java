package com.focus.app.core.dtos;

import com.focus.app.core.models.Task;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDTO {
    private UUID id;

    private String name;
    private String description;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
    }
}
