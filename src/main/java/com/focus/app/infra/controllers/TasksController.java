package com.focus.app.infra.controllers;

import com.focus.app.core.dtos.TaskDTO;
import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.interfaces.TasksService;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskRecord;
import com.focus.app.core.utils.MessageResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<Task> create(@RequestBody @Valid CreateTaskRecord createTaskRecord) {
        User user = authenticationUtils.getUser();
        Task task = tasksService.create(user, createTaskRecord);

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAll() {
        User user = authenticationUtils.getUser();

        List<TaskDTO> tasks = tasksService.findAllByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity delete(@PathVariable UUID taskId) {
        User user = authenticationUtils.getUser();

        tasksService.delete(taskId, user);

        return ResponseEntity.ok(new MessageResponse("Task deleted"));
    }
}
