package com.focus.app.domain.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class TaskLog {
    private UUID id;
    private LocalDate day;
    private LocalTime hour;
    private User user;
    private Task task;

    public TaskLog() {
    }

    public TaskLog(UUID id, LocalDate day, LocalTime hour, User user, Task task) {
        this.id = id;
        this.day = day;
        this.hour = hour;
        this.user = user;
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

