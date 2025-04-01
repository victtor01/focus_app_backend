package com.focus.app.domain.models.task.builder;

import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.taskCategory.TaskCategory;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public class TaskBuilder {

    private UUID id;
    private String name;
    private String description;
    private String color;
    private User user;
    private List<Reminder> reminders = List.of();
    private List<TaskCategory> categories = List.of();

    public TaskBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public TaskBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder color(String color) {
        this.color = color;
        return this;
    }

    public TaskBuilder reminders(List<Reminder> reminders) {
        this.reminders = reminders;
        return this;
    }

    public TaskBuilder user(User user) {
        this.user = user;
        return this;
    }

    public TaskBuilder categories(List<TaskCategory> categories) {
        this.categories = categories;
        return this;
    }

    public Task build() {
        return new Task(this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public User getUser() {
        return user;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public List<TaskCategory> getCategories() {
        return categories;
    }
}
