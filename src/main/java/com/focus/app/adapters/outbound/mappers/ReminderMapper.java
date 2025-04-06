package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaReminderEntity;
import com.focus.app.domain.models.reminders.Reminder;

import java.util.List;

public class ReminderMapper {
    public static JpaReminderEntity toEntity(Reminder reminder) {
        if (reminder == null) return null;

        return JpaReminderEntity.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .daysOfWeek(reminder.getReminderDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.getRecurring())
            .task(TaskMapper.toEntity(reminder.getTask()))
            .user(UserMapper.toEntity(reminder.getUser()))
            .tasksLogs(reminder.getTasksLog().stream().map(TaskLogMapper::toEntity).toList())
            .build();
    }

    public static Reminder toDomain(JpaReminderEntity reminder) {
        if (reminder == null) return null;

        return Reminder.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.isRecurring())
            .task(TaskMapper.toDomain(reminder.getTask()))
            .user(UserMapper.toDomain(reminder.getUser()))
            .tasksLogs(reminder.getTasksLogs().stream().map(TaskLogMapper::toDomain).toList())
            .build();
    }

    public static Reminder toSimple(JpaReminderEntity reminder) {
        if (reminder == null) return null;
        return Reminder.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.isRecurring())
            .build();
    }
}
