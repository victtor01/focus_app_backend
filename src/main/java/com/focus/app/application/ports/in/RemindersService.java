package com.focus.app.application.ports.in;

import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;
import com.focus.app.application.commands.CreateReminderCommand;

import java.util.List;

public interface RemindersService {
    Reminder save(User user, CreateReminderCommand createReminderRecord);
    List<Reminder> findAllByUser(User user);
}
