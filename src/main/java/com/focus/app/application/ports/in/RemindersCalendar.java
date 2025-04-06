package com.focus.app.application.ports.in;


import com.focus.app.domain.models.reminders.Reminder;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface RemindersCalendar {
    Map<LocalDate, List<Reminder>> createRemindersCalendar(UUID userId, LocalDate start, LocalDate end);
}
