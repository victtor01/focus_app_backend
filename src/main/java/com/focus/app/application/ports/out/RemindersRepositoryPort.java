package com.focus.app.application.ports.out;

import com.focus.app.domain.models.Reminder;
import com.focus.app.domain.models.User;

import java.util.List;

public interface RemindersRepositoryPort {
    List<Reminder> findAllByUser(User user);
    Reminder save(Reminder reminder);
}
