package com.focus.app.application.services;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksLogService;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.application.ports.out.TasksLogRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.application.utils.CalendarUtils;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.user.User;
import com.focus.app.application.commands.CreateTaskLogCommand;
import com.focus.app.shared.exceptions.NotFoundException;
import com.focus.app.shared.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TasksLogServiceImplements implements TasksLogService {

    private final TasksLogRepositoryPort tasksLogRepository;
    private final TasksRepositoryPort tasksRepository;
    private final AuthenticationUtils authenticationUtils;
    private final RemindersRepositoryPort remindersRepositoryPort;

    @Autowired
    public TasksLogServiceImplements(TasksLogRepositoryPort tasksLogRepository, TasksRepositoryPort tasksRepository, AuthenticationUtils authenticationUtils,
                                     RemindersRepositoryPort remindersRepositoryPort) {
        this.tasksLogRepository = tasksLogRepository;
        this.tasksRepository = tasksRepository;
        this.authenticationUtils = authenticationUtils;
        this.remindersRepositoryPort = remindersRepositoryPort;
    }

    @Override
    public TaskLog create(CreateTaskLogCommand createTaskLogRecord, User user) {
        Task task = this.tasksRepository.findById(createTaskLogRecord.taskId()).orElseThrow(
            () -> new NotFoundException("task to add log not found!")
        );

        TaskLog taskLog = new TaskLog();
        taskLog.setHour(createTaskLogRecord.hour());
        taskLog.setDay(createTaskLogRecord.day());
        taskLog.setUser(user);
        taskLog.setTask(task);

        if (createTaskLogRecord.reminderId() != null) {
            Reminder reminder = this.remindersRepositoryPort.findById(createTaskLogRecord.reminderId())
                .orElseThrow(() -> new NotFoundException("reminder not found!"));

            taskLog.setReminder(reminder);
        }

        return this.tasksLogRepository.save(taskLog);
    }

    @Override
    public void delete(UUID taskLogId, UUID userId) {
        TaskLog taskLog = this.tasksLogRepository.findById(taskLogId)
            .orElseThrow(() -> new NotFoundException("TaskLog not found"));

        if (!taskLog.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("this task not belongs to you");
        }

        this.tasksLogRepository.deleteById(taskLog.getId());
    }

    private Map<LocalDate, List<TaskLogDTO>> populateCalendar(LocalDate start, LocalDate end) {
        Map<LocalDate, List<TaskLogDTO>> result = new TreeMap<>();

        start.datesUntil(end.plusDays(1)).toList()
            .forEach(day -> result.put(day, new ArrayList<>()));

        return result;
    }

    @Override
    public Map<LocalDate, List<TaskLogDTO>> getCalendar(LocalDate start, LocalDate end) {
        User user = authenticationUtils.getUser();

        List<TaskLogDTO> tasksLogDTOs = tasksLogRepository
            .findByDayBetweenAndUserId(start, end, user.getId())
            .stream()
            .map(TaskLogDTO::toEntity)
            .toList();

        Map<LocalDate, List<TaskLogDTO>> calendar = this.populateCalendar(start, end);

        tasksLogDTOs.forEach(taskLogDTO -> calendar
            .get(taskLogDTO.getDay())
            .add(taskLogDTO));

        return calendar;
    }

    @Override
    public List<TaskLog> findAllByDate(LocalDate dateT) {
        UUID userId = authenticationUtils.getId();

        return tasksLogRepository.findByDayAndUserId(dateT, userId);
    }
}
