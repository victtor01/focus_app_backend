package com.focus.app.core.interfaces;

import com.focus.app.core.dtos.ReminderDTO;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateReminderRecord;

import java.util.List;

public interface RemindersService {
    ReminderDTO create(User user, CreateReminderRecord createReminderRecord);

    List<ReminderDTO> findAllByUser(User user);
}
