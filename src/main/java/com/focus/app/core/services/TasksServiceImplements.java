package com.focus.app.core.services;

import com.focus.app.core.dtos.TaskDTO;
import com.focus.app.core.interfaces.TasksService;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskRecord;
import com.focus.app.core.utils.Color;
import com.focus.app.infra.config.errors.BadRequestException;
import com.focus.app.infra.config.errors.NotFoundException;
import com.focus.app.infra.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TasksServiceImplements implements TasksService {

    private final TasksRepository tasksRepository;

    @Autowired
    public TasksServiceImplements(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    private String createColor(String color) {
        return color != null ? new Color(color).toString() : null;
    }

    @Override
    public Task create(User user, CreateTaskRecord createTaskRecord) {
        String color = this.createColor(createTaskRecord.color());

        Task task = Task.builder()
            .name(createTaskRecord.name())
            .description(createTaskRecord.description())
            .color(color)
            .user(user)
            .build();

        return tasksRepository.save(task);
    }

    @Override
    public List<TaskDTO> findByUser(User user) {
        return this.tasksRepository.findAllByUser(user);
    }

    @Override
    public void delete(UUID taskId, User user) {
        Task task = this.tasksRepository.findById(taskId).orElseThrow(
            () -> new NotFoundException("this task not found!")
        );

        if (!task.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("this task not belongs to user");
        }

        this.tasksRepository.delete(task);
    }
}
