package com.focus.app.application.services;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksLogService;
import com.focus.app.application.ports.out.TasksLogRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.User;
import com.focus.app.application.commands.CreateTaskLogCommand;
import com.focus.app.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TasksLogServiceImplements implements TasksLogService {

    private final TasksLogRepositoryPort tasksLogRepository;
    private final TasksRepositoryPort tasksRepository;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public TasksLogServiceImplements(TasksLogRepositoryPort tasksLogRepository, TasksRepositoryPort tasksRepository, AuthenticationUtils authenticationUtils) {
        this.tasksLogRepository = tasksLogRepository;
        this.tasksRepository = tasksRepository;
        this.authenticationUtils = authenticationUtils;
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

        this.tasksLogRepository.save(taskLog);

        return taskLog;
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

        Map<LocalDate, List<TaskLogDTO>> calendar = populateCalendar(start, end);

        tasksLogDTOs.forEach(taskLogDTO -> calendar
            .get(taskLogDTO.getDay())
            .add(taskLogDTO));

        return calendar;
    }

    @Override
    public List<TaskLogDTO> findAllByDate(LocalDate dateT) {
        UUID userId = authenticationUtils.getId();

        return tasksLogRepository.findByDayAndUserId(dateT, userId)
            .stream()
            .map(TaskLogDTO::toEntity)
            .toList();
    }
}
