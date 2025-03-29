package com.focus.app.application.services;

import com.focus.app.application.commands.CreateTaskCategoryCommand;
import com.focus.app.application.ports.in.TasksCategoriesService;
import com.focus.app.application.ports.out.TasksCategoriesRepositoryPort;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.domain.models.TaskCategory;
import com.focus.app.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoriesServiceImplements implements TasksCategoriesService {

    private final TasksCategoriesRepositoryPort tasksCategoriesRepositoryPort;

    @Autowired
    public TaskCategoriesServiceImplements(TasksCategoriesRepositoryPort tasksCategoriesRepositoryPort, TasksRepositoryPort tasksRepositoryPort) {
        this.tasksCategoriesRepositoryPort = tasksCategoriesRepositoryPort;
    }

    @Override
    public TaskCategory create(CreateTaskCategoryCommand createTaskCategoryRecord, User user) {

        TaskCategory taskCategory = TaskCategory.builder()
            .name(createTaskCategoryRecord.name())
            .color(createTaskCategoryRecord.color())
            .user(user)
            .build();

        return this.tasksCategoriesRepositoryPort.save(taskCategory);
    }

    @Override
    public List<TaskCategory> findAllByUser(User user) {
        return this.tasksCategoriesRepositoryPort.findAllByUser(user);
    }
}

