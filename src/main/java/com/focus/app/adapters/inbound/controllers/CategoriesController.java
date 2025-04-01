package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.request.CreateTaskCategoryRecord;
import com.focus.app.adapters.inbound.dtos.response.TaskCategoryResponse;
import com.focus.app.application.commands.CreateTaskCategoryCommand;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.TasksCategoriesService;
import com.focus.app.domain.models.taskCategory.TaskCategory;
import com.focus.app.domain.models.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final TasksCategoriesService tasksCategoriesService;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public CategoriesController(TasksCategoriesService tasksCategoriesService, AuthenticationUtils authenticationUtils) {
        this.tasksCategoriesService = tasksCategoriesService;
        this.authenticationUtils = authenticationUtils;
    }

    @PostMapping
    public ResponseEntity<TaskCategoryResponse> create(@RequestBody @Valid CreateTaskCategoryRecord createTaskCategoryRecord) {
        CreateTaskCategoryCommand createTaskCategoryCommand = new CreateTaskCategoryCommand(
            createTaskCategoryRecord.name(),
            createTaskCategoryRecord.color()
        );

        User user = this.authenticationUtils.getUser();

        TaskCategory taskCategory = this.tasksCategoriesService.create(createTaskCategoryCommand, user);

        return ResponseEntity.ok(new TaskCategoryResponse(
            taskCategory.getId(),
            taskCategory.getName(),
            taskCategory.getColor()
        ));
    }

    @GetMapping
    public ResponseEntity<List<TaskCategory>> findAllByUser() {
        User user = this.authenticationUtils.getUser();

        List<TaskCategory> categories = this.tasksCategoriesService.findAllByUser(user);

        return ResponseEntity.ok(categories);
    }
}
