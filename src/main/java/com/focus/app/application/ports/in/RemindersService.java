package com.focus.app.application.ports.in;

import com.focus.app.adapters.inbound.dtos.ReminderDTO;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateReminderRecord;

import java.util.List;

public interface RemindersService {
    ReminderDTO create(User user, CreateReminderRecord createReminderRecord);

    List<ReminderDTO> findAllByUser(User user);
}
