package com.focus.app.domain.models.reminders.builder;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.enums.ReminderType;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class ReminderBuilder {
    private UUID id;
    private List<LocalDate> customReminderDates = List.of();
    private List<LocalDate> reminderDaysOfWeek = List.of();
    private ReminderType reminderType;
    private LocalTime reminderHour;
    private boolean isRecurring;
    private Task task;
    private User user;

    public Reminder build() {
        return new Reminder(this);
    }

    public ReminderBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public ReminderBuilder customReminderDates(List<LocalDate> customReminderDates) {
        this.customReminderDates = customReminderDates;
        return this;
    }

    public ReminderBuilder isRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
        return this;
    }

    public ReminderBuilder reminderDaysOfWeek(List<LocalDate> reminderDaysOfWeek) {
        this.reminderDaysOfWeek = reminderDaysOfWeek;
        return this;
    }

    public ReminderBuilder reminderType(ReminderType reminderType) {
        this.reminderType = reminderType;
        return this;
    }

    public ReminderBuilder reminderHour(LocalTime reminderHour) {
        this.reminderHour = reminderHour;
        return this;
    }

    public ReminderBuilder task(Task task) {
        this.task = task;
        return this;
    }

    public ReminderBuilder user(User user) {
        this.user = user;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public List<LocalDate> getCustomReminderDates() {
        return customReminderDates;
    }

    public List<LocalDate> getReminderDaysOfWeek() {
        return reminderDaysOfWeek;
    }

    public ReminderType getReminderType() {
        return reminderType;
    }

    public LocalTime getReminderHour() {
        return reminderHour;
    }

    public Task getTask() {
        return task;
    }

    public User getUser() {
        return user;
    }

    public boolean getIsRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }
}
