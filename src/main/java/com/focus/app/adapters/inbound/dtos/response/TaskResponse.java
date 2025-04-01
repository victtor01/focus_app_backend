package com.focus.app.adapters.inbound.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.focus.app.domain.models.taskCategory.TaskCategory;
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
public class TaskResponse {
    private UUID id;
    private String name;
    private String description;
    private List<ReminderResponse> reminders;
    private List<TaskCategoryResponse> categories;
}
