package com.focus.app.application.ports.out;

import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RemindersRepositoryPort {
    List<Reminder> findAllByUser(User user);
    Reminder save(Reminder reminder);
    Optional<Reminder> findById(UUID reminderId);
    void delete(UUID reminderId);
}
