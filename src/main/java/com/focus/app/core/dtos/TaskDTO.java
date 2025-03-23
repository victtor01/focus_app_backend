package com.focus.app.core.dtos;

import com.focus.app.core.models.Reminder;
import com.focus.app.core.models.Task;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private UUID id;
    private String name;
    private String description;
    private List<ReminderDTO> reminders;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();

        this.reminders = task.getReminders()
            .stream()
            .map(ReminderDTO::new)
            .collect(Collectors.toList());
    }
}
