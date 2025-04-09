package com.focus.app.adapters.inbound.mappers;

import com.focus.app.adapters.inbound.dtos.response.ReminderResponse;
import com.focus.app.domain.models.reminders.Reminder;

public class ReminderMapper {
    public static ReminderResponse toResponse(Reminder reminder) {
        if (reminder == null) return null;
        return ReminderResponse.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getReminderDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .tasksLogs(reminder.getTasksLog().stream().map(TaskLogMapper::toSimpleResponse).toList())
            .task(TaskMapper.toSimpleResponse(reminder.getTask()))
            .build();
    }

    public static ReminderResponse toSimpleResponse(Reminder reminder) {
        if (reminder == null) return null;

        return ReminderResponse.builder()
            .id(reminder.getId())
            .customReminderDates(reminder.getCustomReminderDates())
            .reminderDaysOfWeek(reminder.getReminderDaysOfWeek())
            .reminderType(reminder.getReminderType())
            .build();
    }
}
