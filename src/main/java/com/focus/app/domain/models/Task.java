package com.focus.app.domain.models;

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

    public Task(UUID id, String name, String description, String color, List<Reminder> reminders, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.reminders = reminders;
        this.user = user;
    }

    public Task() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<TaskCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<TaskCategory> categories) {
        this.categories = categories;
    }
}
