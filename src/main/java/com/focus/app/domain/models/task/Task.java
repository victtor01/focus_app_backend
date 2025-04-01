package com.focus.app.domain.models.task;

import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.taskCategory.TaskCategory;
import com.focus.app.domain.models.task.builder.TaskBuilder;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public class Task {
    private UUID id;
    private String name;
    private String description;
    private String color;
    private List<Reminder> reminders;
    private User user;
    private List<TaskCategory> categories;

    public Task() {
    }

    public Task(UUID id, String name, String description, String color, List<Reminder> reminders, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.reminders = reminders;
        this.user = user;
    }

    public Task(TaskBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.description = builder.getDescription();
        this.color = builder.getColor();
        this.reminders = builder.getReminders();
        this.user = builder.getUser();
        this.categories = builder.getCategories();
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public String getColor() {
        return color;
    }

    public List<TaskCategory> getCategories() {
        return categories;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }
}
