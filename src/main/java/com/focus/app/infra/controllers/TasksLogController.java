package com.focus.app.infra.controllers;

import com.focus.app.core.dtos.TaskLogDTO;
import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.interfaces.TasksLogService;
import com.focus.app.core.models.TaskLog;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskLogRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tasks-log")
public class TasksLogController {
    private final TasksLogService tasksLogService;
    private final AuthenticationUtils authenticationUtils;

    public TasksLogController(TasksLogService tasksService, AuthenticationUtils authenticationUtils) {
        this.tasksLogService = tasksService;
        this.authenticationUtils = authenticationUtils;
    }

    @PostMapping()
    public ResponseEntity<TaskLog> create(@RequestBody @Valid CreateTaskLogRecord createTaskLogRecord) {
        User user = this.authenticationUtils.getUser();

        TaskLog created = this.tasksLogService.create(createTaskLogRecord, user);

        return ResponseEntity.ok(created);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<LocalDate, List<TaskLogDTO>>> getTaskLogsForUser(
        @RequestParam("start") String startDateStr,
        @RequestParam("end") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        var taskLogs = tasksLogService.getCalendar(startDate, endDate);

        return ResponseEntity.ok(taskLogs);
    }
}
