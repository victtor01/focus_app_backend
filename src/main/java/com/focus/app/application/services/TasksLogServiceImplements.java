package com.focus.app.application.services;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksLogService;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.application.ports.out.TasksLogRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.application.utils.CalendarUtils;
import com.focus.app.domain.enums.ReminderType;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.user.User;
import com.focus.app.application.commands.CreateTaskLogCommand;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.shared.exceptions.NotFoundException;
import com.focus.app.shared.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
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
        Task task = tasksRepository.findById(createTaskLogRecord.taskId())
            .orElseThrow(() -> new NotFoundException("task to add log not found!"));

        TaskLog taskLog = new TaskLog();
        taskLog.setHour(createTaskLogRecord.hour());
        taskLog.setDay(createTaskLogRecord.day());
        taskLog.setDuration(createTaskLogRecord.duration());
        taskLog.setUser(user);
        taskLog.setTask(task);

        if (createTaskLogRecord.reminderId() != null) {
            Reminder reminder = remindersRepositoryPort.findById(createTaskLogRecord.reminderId())
                .orElseThrow(() -> new NotFoundException("reminder not found!"));

            validateReminder(reminder, createTaskLogRecord.day());
            taskLog.setReminder(reminder);
        }

        return tasksLogRepository.save(taskLog);
    }

    private void validateReminder(Reminder reminder, LocalDate logDay) {
        if (!reminder.getRecurring()) {
            Month expectedMonth = reminder.getCustomReminderDates().getFirst().getMonth();
            if (!logDay.getMonth().equals(expectedMonth)) {
                throw new BadRequestException("Dates must be in the same month");
            }
        }

        List<LocalDate> referenceDates = reminder.getReminderType() == ReminderType.WEEKLY
            ? reminder.getReminderDaysOfWeek()
            : reminder.getCustomReminderDates();

        boolean match = referenceDates.stream()
            .anyMatch(date -> date.getDayOfWeek() == logDay.getDayOfWeek());

        if (!match) {
            throw new BadRequestException("Dates do not match");
        }
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
