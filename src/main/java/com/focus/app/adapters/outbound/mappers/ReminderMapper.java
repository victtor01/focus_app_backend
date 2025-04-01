package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaReminderEntity;
import com.focus.app.domain.models.reminders.Reminder;

public class ReminderMapper {
    public static JpaReminderEntity toEntity(Reminder reminder) {
        return JpaReminderEntity.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .daysOfWeek(reminder.getReminderDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.getRecurring())
            .task(TaskMapper.toEntity(reminder.getTask()))
            .user(UserMapper.toEntity(reminder.getUser()))
            .build();
    }

    public static Reminder toDomain(JpaReminderEntity reminder) {
        if(reminder == null) return null;

        return Reminder.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.isRecurring())
            .task(TaskMapper.toDomain(reminder.getTask()))
            .user(UserMapper.toDomain(reminder.getUser()))
            .build();
    }

    public static Reminder toSimple(JpaReminderEntity reminder) {
        return Reminder.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .isRecurring(reminder.isRecurring())
            .build();
    }
}
