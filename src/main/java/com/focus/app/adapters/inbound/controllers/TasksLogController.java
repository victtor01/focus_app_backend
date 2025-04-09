package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.adapters.inbound.dtos.request.CreateTaskLogRequest;
import com.focus.app.adapters.inbound.dtos.response.TaskLogResponse;
import com.focus.app.adapters.inbound.mappers.TaskLogMapper;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksLogService;
import com.focus.app.application.commands.CreateTaskLogCommand;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.user.User;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TaskLogResponse> create(@RequestBody @Valid CreateTaskLogRequest createTaskLogRequest) {
        User user = this.authenticationUtils.getUser();

        CreateTaskLogCommand createTaskLogCommand = new CreateTaskLogCommand(
            createTaskLogRequest.day(),
            createTaskLogRequest.taskId(),
            createTaskLogRequest.reminderId(),
            createTaskLogRequest.hour(),
            createTaskLogRequest.duration()
        );

        TaskLog created = this.tasksLogService.create(createTaskLogCommand, user);

        return ResponseEntity.ok(TaskLogMapper.toResponse(created));
    }

    @GetMapping("{date}")
    public ResponseEntity<List<TaskLogResponse>> findAllByDate(@PathVariable LocalDate date) {
        List<TaskLogResponse> tasks = this.tasksLogService.findAllByDate(date)
            .stream()
            .map(TaskLogMapper::toResponse)
            .toList();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<LocalDate, List<TaskLogDTO>>> getTaskLogsForUser(
        @RequestParam("start") String startDateStr,
        @RequestParam("end") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        Map<LocalDate, List<TaskLogDTO>> taskLogs = tasksLogService.getCalendar(startDate, endDate);

        return ResponseEntity.ok(taskLogs);
    }

    @DeleteMapping("{taskLogId}")
    public ResponseEntity delete(@PathVariable UUID taskLogId) {
        UUID userId = authenticationUtils.getId();

        this.tasksLogService.delete(taskLogId, userId);

        return ResponseEntity.ok("deleted");
    }
}
