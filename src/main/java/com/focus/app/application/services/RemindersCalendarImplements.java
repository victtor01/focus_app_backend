package com.focus.app.application.services;

import com.focus.app.application.ports.in.RemindersCalendar;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.application.utils.CalendarUtils;
import com.focus.app.domain.models.reminders.Reminder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RemindersCalendarImplements implements RemindersCalendar {
    private final RemindersRepositoryPort remindersRepositoryPort;

    public RemindersCalendarImplements(RemindersRepositoryPort remindersRepositoryPort) {
        this.remindersRepositoryPort = remindersRepositoryPort;
    }

    @Override
    public Map<LocalDate, List<Reminder>> createRemindersCalendar(UUID userId, LocalDate start, LocalDate end) {
        List<Reminder> reminders = remindersRepositoryPort.findAllByUserId(userId);
        Map<LocalDate, List<Reminder>> calendar = CalendarUtils.populateBetween(start, end);

        for (Reminder reminder : reminders) {
            switch (reminder.getReminderType()) {
                case CUSTOM -> handleCustomReminder(reminder, start, end, calendar);
                case WEEKLY -> handleWeeklyReminder(reminder, start, end, calendar);
            }
        }

        return calendar;
    }

    private void handleCustomReminder(Reminder reminder, LocalDate start, LocalDate end, Map<LocalDate, List<Reminder>> calendar) {
        List<LocalDate> customDates = reminder.getCustomReminderDates();
        LocalDate firstDate = getEarliestDate(customDates);

        if (firstDate == null) return;

        for (LocalDate date : customDates) {
            if (!reminder.getRecurring()) {
                addIfInRange(calendar, date, start, end, reminder);
            } else {
                LocalDate current = start.withDayOfMonth(1);
                while (!current.isAfter(end)) {
                    LocalDate recurringDate = safeWithDayOfMonth(current, date.getDayOfMonth());

                    if (recurringDate != null && isInRange(recurringDate, start, end) && !recurringDate.isBefore(firstDate)) {
                        calendar.get(recurringDate).add(reminder);
                    }

                    current = current.plusMonths(1);
                }
            }
        }
    }

    private void handleWeeklyReminder(Reminder reminder, LocalDate start, LocalDate end, Map<LocalDate, List<Reminder>> calendar) {
        List<LocalDate> weekDates = reminder.getReminderDaysOfWeek();
        LocalDate firstDate = getEarliestDate(weekDates);

        if (firstDate == null) return;

        Set<DayOfWeek> daysOfWeek = weekDates.stream()
            .map(LocalDate::getDayOfWeek)
            .collect(Collectors.toSet());

        if (!reminder.getRecurring()) {
            for (LocalDate date : weekDates) {
                addIfInRange(calendar, date, start, end, reminder);
            }
        } else {
            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                if (!date.isBefore(firstDate) && daysOfWeek.contains(date.getDayOfWeek())) {
                    calendar.get(date).add(reminder);
                }
            }
        }
    }

    private LocalDate getEarliestDate(List<LocalDate> dates) {
        return dates.stream()
            .min(LocalDate::compareTo)
            .orElse(null);
    }

    private boolean isInRange(LocalDate date, LocalDate start, LocalDate end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }

    private void addIfInRange(Map<LocalDate, List<Reminder>> calendar, LocalDate date, LocalDate start, LocalDate end, Reminder reminder) {
        if (isInRange(date, start, end)) {
            calendar.get(date).add(reminder);
        }
    }

    private LocalDate safeWithDayOfMonth(LocalDate baseDate, int dayOfMonth) {
        try {
            return baseDate.withDayOfMonth(dayOfMonth);
        } catch (Exception e) {
            return null;
        }
    }
}

