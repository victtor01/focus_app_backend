package com.focus.app.application.ports.in;

import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;
import com.focus.app.application.commands.CreateReminderCommand;

import java.util.List;
import java.util.UUID;

public interface RemindersService {
    Reminder save(User user, CreateReminderCommand createReminderRecord);
    List<Reminder> findAllByUser(User user);
    void delete(User user, UUID reminderId);
}
