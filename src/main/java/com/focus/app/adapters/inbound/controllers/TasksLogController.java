package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksLogService;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateTaskLogRecord;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @GetMapping("{date}")
    public ResponseEntity findAllByDate(@PathVariable LocalDate date) {
        List<TaskLogDTO> tasks = this.tasksLogService.findAllByDate(date);

        return ResponseEntity.ok(tasks);
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
