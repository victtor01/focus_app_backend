package com.focus.app.core.services;

import com.focus.app.core.dtos.TaskLogDTO;
import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.interfaces.TasksLogService;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.TaskLog;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskLogRecord;
import com.focus.app.infra.config.errors.NotFoundException;
import com.focus.app.infra.repositories.TasksLogRepository;
import com.focus.app.infra.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TasksLogServiceImplements implements TasksLogService {

    private final TasksLogRepository tasksLogRepository;
    private final TasksRepository tasksRepository;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public TasksLogServiceImplements(TasksLogRepository tasksLogRepository, TasksRepository tasksRepository, AuthenticationUtils authenticationUtils) {
        this.tasksLogRepository = tasksLogRepository;
        this.tasksRepository = tasksRepository;
        this.authenticationUtils = authenticationUtils;
    }

    @Override
    public TaskLog create(CreateTaskLogRecord createTaskLogRecord, User user) {
        Task task = this.tasksRepository.findById(createTaskLogRecord.taskId()).orElseThrow(
            () -> new NotFoundException("task to add log not found!")
        );

        TaskLog taskLog = TaskLog.builder()
            .day(createTaskLogRecord.day())
            .task(task)
            .user(user)
            .build();

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

        List<TaskLogDTO> tasksLogDTOs = tasksLogRepository.findByDayAndUserId(dateT, userId)
            .stream()
            .map(TaskLogDTO::toEntity)
            .toList();

        return tasksLogDTOs;
    }
}
