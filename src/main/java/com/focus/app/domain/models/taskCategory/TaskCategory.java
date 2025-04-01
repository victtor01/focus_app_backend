package com.focus.app.domain.models.taskCategory;

import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.taskCategory.builder.TaskCategoryBuilder;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public class TaskCategory {
    private UUID id;
    private String name;
    private String color;
    private List<Task> tasks;
    private User user;

    public TaskCategory(UUID id, String name, String color, List<Task> tasks, User user) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.tasks = tasks;
        this.user = user;
    }

    public TaskCategory(TaskCategoryBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.color = builder.getColor();
        this.tasks = builder.getTasks();
        this.user = builder.getUser();
    }

    public static TaskCategoryBuilder builder() {
        return new TaskCategoryBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public User getUser() {
        return user;
    }
}
