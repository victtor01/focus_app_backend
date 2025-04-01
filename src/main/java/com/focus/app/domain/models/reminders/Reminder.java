package com.focus.app.domain.models.reminders;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.enums.ReminderType;
import com.focus.app.domain.models.reminders.builder.ReminderBuilder;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Reminder {
    private UUID id;
    private List<LocalDate> customReminderDates;
    private List<LocalDate> reminderDaysOfWeek;
    private ReminderType reminderType = ReminderType.WEEKLY;
    private LocalTime reminderHour;
    private Task task;
    private User user;

    private boolean isRecurring;

    public Reminder() {
    }

    public Reminder(ReminderBuilder reminderBuilder) {
        this.id = reminderBuilder.getId();
        this.customReminderDates = reminderBuilder.getCustomReminderDates();
        this.reminderDaysOfWeek = reminderBuilder.getReminderDaysOfWeek();
        this.reminderType = reminderBuilder.getReminderType();
        this.reminderHour = reminderBuilder.getReminderHour();
        this.isRecurring = reminderBuilder.getIsRecurring();
        this.task = reminderBuilder.getTask();
        this.user = reminderBuilder.getUser();
    }

    public Reminder(UUID id, List<LocalDate> localDateTime, List<LocalDate> daysOfWeek, boolean repeat, Task task, User user, ReminderType reminderType) {
        this.id = id;
        this.customReminderDates = localDateTime;
        this.reminderDaysOfWeek = daysOfWeek;
        this.reminderType = reminderType;
        this.isRecurring = repeat;
        this.task = task;
        this.user = user;
    }

    public static ReminderBuilder builder() {
        return new ReminderBuilder();
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

    public Task getTask() {
        return task;
    }

    public User getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalTime getReminderHour() {
        return reminderHour;
    }

    public boolean getRecurring() {
        return isRecurring;
    }

    public void setCustomReminderDates(List<LocalDate> customReminderDates) {
        this.customReminderDates = customReminderDates;
    }

    public void setReminderDaysOfWeek(List<LocalDate> reminderDaysOfWeek) {
        this.reminderDaysOfWeek = reminderDaysOfWeek;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRecurring(boolean recurring) {
        this.isRecurring = recurring;
    }

    public void setReminderHour(LocalTime reminderHour) {
        this.reminderHour = reminderHour;
    }

    public ReminderType getReminderType() {
        return reminderType;
    }

    public void setReminderType(ReminderType reminderType) {
        this.reminderType = reminderType;
    }
}
