package com.focus.app.application.services;

import com.focus.app.application.ports.in.RemindersService;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.application.utils.WeekValidator;
import com.focus.app.domain.enums.ReminderType;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;
import com.focus.app.application.commands.CreateReminderCommand;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.shared.exceptions.NotFoundException;
import com.focus.app.shared.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RemindersServiceImplements implements RemindersService {

    private final RemindersRepositoryPort remindersRepository;
    private final TasksRepositoryPort tasksRepository;

    @Autowired
    public RemindersServiceImplements(RemindersRepositoryPort remindersRepository, TasksRepositoryPort tasksRepository) {
        this.remindersRepository = remindersRepository;
        this.tasksRepository = tasksRepository;
    }

    private void isValidReminderTypeWeek(ReminderType reminderType, List<LocalDate> daysOfWeek) {
        if (reminderType == ReminderType.WEEKLY && daysOfWeek == null) {
            throw new BadRequestException("days of week is required when reminderType is WEEKLY");
        }
    }

    private void isValidReminderTypeCustom(ReminderType reminderType, List<LocalDate> customDates) {
        if (reminderType == ReminderType.CUSTOM && customDates == null) {
            throw new BadRequestException("custom days is required when reminderType is CUSTOM");
        }
    }

    @Override
    public Reminder save(User user, CreateReminderCommand createReminderRecord) {
        Task task = this.tasksRepository.findById(createReminderRecord.taskId()).orElseThrow(
            () -> new NotFoundException("task to add reminder not found")
        );

        ReminderType reminderType = createReminderRecord.reminderType();
        this.isValidReminderTypeWeek(reminderType, createReminderRecord.daysOfWeek());
        this.isValidReminderTypeCustom(reminderType, createReminderRecord.customRemindersDates());

        switch (reminderType) {
            case WEEKLY -> {
                boolean isValidWeek = WeekValidator.areDatesInSameWeek(createReminderRecord.daysOfWeek());
                if (!isValidWeek) {
                    throw new BadRequestException("week days is invalid!");
                }
            }
            case CUSTOM -> {
                boolean isValidCustomDates = WeekValidator.areDatesInSameMonth(createReminderRecord.customRemindersDates());
                if (!isValidCustomDates) {
                    throw new BadRequestException("month days is invalid format");
                }
            }
        }

        Reminder reminder = Reminder.builder()
            .customReminderDates(createReminderRecord.customRemindersDates())
            .reminderDaysOfWeek(createReminderRecord.daysOfWeek())
            .reminderType(reminderType)
            .reminderHour(null)
            .user(user)
            .task(task)
            .build();

        return this.remindersRepository.save(reminder);
    }

    @Override
    public List<Reminder> findAllByUser(User user) {
        return this.remindersRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void delete(User user, UUID reminderId) {
        Reminder reminder = this.remindersRepository.findById(reminderId).orElseThrow(
            () -> new NotFoundException("not found reminder")
        );

        User userIn = reminder.getUser();

        if (!userIn.getId().equals(user.getId())) {
            throw new UnauthorizedException("this reminder not belongs to you");
        }

        this.remindersRepository.delete(reminderId);
    }
}
