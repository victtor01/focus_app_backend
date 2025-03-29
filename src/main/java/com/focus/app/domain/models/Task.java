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

    public Task(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.color = builder.color;
        this.reminders = builder.reminders;
        this.user = builder.user;
        this.categories = builder.categories;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String description;
        private String color;
        private User user;
        private List<Reminder> reminders = List.of();
        private List<TaskCategory> categories = List.of();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder reminders(List<Reminder> reminders) {
            this.reminders = reminders;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder categories(List<TaskCategory> categories) {
            this.categories = categories;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
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
}
