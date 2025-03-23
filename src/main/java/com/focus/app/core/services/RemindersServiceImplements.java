package com.focus.app.core.services;

import com.focus.app.core.dtos.ReminderDTO;
import com.focus.app.core.interfaces.RemindersService;
import com.focus.app.core.models.Reminder;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateReminderRecord;
import com.focus.app.infra.config.errors.NotFoundException;
import com.focus.app.infra.repositories.RemindersRepository;
import com.focus.app.infra.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindersServiceImplements implements RemindersService {

    private final RemindersRepository remindersRepository;
    private final TasksRepository  tasksRepository;

    @Autowired
    public RemindersServiceImplements(RemindersRepository remindersRepository, TasksRepository tasksRepository) {
        this.remindersRepository = remindersRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public ReminderDTO create(User user, CreateReminderRecord createReminderRecord) {

        Task task = this.tasksRepository.findById(createReminderRecord.taskId()).orElseThrow(
            () -> new NotFoundException("task to add reminder not found")
        );

        Reminder reminder = Reminder.builder()
            .reminderTime(createReminderRecord.reminderTime())
            .daysOfWeek(createReminderRecord.daysOfWeek())
            .user(user)
            .task(task)
            .build();

        Reminder created = this.remindersRepository.save(reminder);

        return new ReminderDTO(created);
    }

    @Override
    public List<ReminderDTO> findAllByUser(User user) {
        return this.remindersRepository.findAllByUser(user);
    }
}
