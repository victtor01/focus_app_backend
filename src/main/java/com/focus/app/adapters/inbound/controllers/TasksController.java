package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.request.CreateTaskRequest;
import com.focus.app.adapters.inbound.dtos.response.TaskResponse;
import com.focus.app.adapters.inbound.mappers.TaskMapper;
import com.focus.app.application.commands.CreateTaskCommand;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksService;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;
import com.focus.app.shared.utils.MessageResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final AuthenticationUtils authenticationUtils;
    private final TasksService tasksService;

    @Autowired
    public TasksController(AuthenticationUtils authenticationUtils, TasksService tasksService) {
        this.authenticationUtils = authenticationUtils;
        this.tasksService = tasksService;
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid CreateTaskRequest createTaskRecord) {

        User user = authenticationUtils.getUser();

        CreateTaskCommand createTaskCommand = new CreateTaskCommand(
            createTaskRecord.name(),
            createTaskRecord.description(),
            createTaskRecord.color(),
            createTaskRecord.categoriesIds()
        );

        Task task = tasksService.create(user, createTaskCommand);

        return ResponseEntity.status(HttpStatus.OK).body(TaskMapper.toResponse(task));
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskResponse> findById(@PathVariable UUID taskId) {
        User user = authenticationUtils.getUser();
        Task task = tasksService.findByIdAndUser(taskId, user);

        return ResponseEntity.ok(TaskMapper.toResponse(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        User user = authenticationUtils.getUser();

        List<TaskResponse> tasks = tasksService.findAllByUser(user)
            .stream()
            .map(TaskMapper::toResponse)
            .toList();

        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable UUID taskId) {
        User user = authenticationUtils.getUser();
        tasksService.delete(taskId, user);

        return ResponseEntity.ok(new MessageResponse("Task deleted"));
    }
}
