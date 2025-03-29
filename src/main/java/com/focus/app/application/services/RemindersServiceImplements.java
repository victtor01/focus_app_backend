package com.focus.app.application.services;

import com.focus.app.adapters.inbound.dtos.ReminderDTO;
import com.focus.app.application.ports.in.RemindersService;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.domain.models.Reminder;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
import com.focus.app.application.commands.CreateReminderCommand;
import com.focus.app.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindersServiceImplements implements RemindersService {

    private final RemindersRepositoryPort remindersRepository;
    private final TasksRepositoryPort tasksRepository;

    @Autowired
    public RemindersServiceImplements(RemindersRepositoryPort remindersRepository, TasksRepositoryPort tasksRepository) {
        this.remindersRepository = remindersRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public ReminderDTO create(User user, CreateReminderCommand createReminderRecord) {

        Task task = this.tasksRepository.findById(createReminderRecord.taskId()).orElseThrow(
            () -> new NotFoundException("task to add reminder not found")
        );

        Reminder reminder = new Reminder();
        reminder.setReminderTime(createReminderRecord.reminderTime());
        reminder.setDaysOfWeek(createReminderRecord.daysOfWeek());
        reminder.setReminderTime(createReminderRecord.reminderTime());
        reminder.setTask(task);
        reminder.setUser(user);


        Reminder created = this.remindersRepository.save(reminder);

        return new ReminderDTO(created);
    }

    @Override
    public List<ReminderDTO> findAllByUser(User user) {
        return this.remindersRepository.findAllByUser(user)
            .stream()
            .map(ReminderDTO::toEntity).toList();
    }
}
