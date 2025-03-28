package com.focus.app.application.services;

import com.focus.app.adapters.inbound.dtos.TaskDTO;
import com.focus.app.application.ports.in.TasksService;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateTaskRecord;
import com.focus.app.shared.utils.Color;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TasksServiceImplements implements TasksService {

    private final TasksRepositoryPort tasksRepository;

    @Autowired
    public TasksServiceImplements(TasksRepositoryPort tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    private String createColor(String color) {
        return color != null ? new Color(color).toString() : null;
    }

    @Override
    public Task create(User user, CreateTaskRecord createTaskRecord) {
        String color = this.createColor(createTaskRecord.color());

        Task task = new Task();
        task.setName(createTaskRecord.name());
        task.setDescription(createTaskRecord.description());
        task.setColor(color);
        task.setUser(user);

        return tasksRepository.save(task);
    }

    @Override
    public List<TaskDTO> findAllByUser(User user) {
        List<Task> tasks = this.tasksRepository.findAllByUser(user);
        return tasks.stream().map(TaskDTO::toEntity).toList();
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
