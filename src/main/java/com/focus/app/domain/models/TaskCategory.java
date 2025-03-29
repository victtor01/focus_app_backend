package com.focus.app.domain.models;

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

    public TaskCategory(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.color = builder.color;
        this.tasks = builder.tasks;
        this.user = builder.user;
    }

    public static Builder builder() {
        return new Builder();
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

    public static class Builder {
        private UUID id;
        private String name;
        private String color;
        private User user;

        private List<Task> tasks = List.of();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder tasks(List<Task> task) {
            this.tasks = task;
            return this;
        }

        public TaskCategory build() {
            return new TaskCategory(this);
        }
    }
}
