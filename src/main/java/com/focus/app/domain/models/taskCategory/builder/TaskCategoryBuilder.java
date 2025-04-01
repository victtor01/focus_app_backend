package com.focus.app.domain.models.taskCategory.builder;

import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.taskCategory.TaskCategory;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public class TaskCategoryBuilder {
    private UUID id;
    private String name;
    private String color;
    private User user;

    private List<Task> tasks = List.of();

    public TaskCategoryBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public TaskCategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TaskCategoryBuilder color(String color) {
        this.color = color;
        return this;
    }

    public TaskCategoryBuilder user(User user) {
        this.user = user;
        return this;
    }

    public TaskCategoryBuilder tasks(List<Task> task) {
        this.tasks = task;
        return this;
    }

    public TaskCategory build() {
        return new TaskCategory(this);
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

    public User getUser() {
        return user;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
