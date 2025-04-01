package com.focus.app.application.ports.in;

import com.focus.app.application.commands.CreateTaskCommand;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public interface TasksService {
    Task create(User user, CreateTaskCommand createTaskRecord);
    Task findByIdAndUser(UUID taskId, User user);
    List<Task> findAllByUser(User user);
    void delete(UUID taskId, User user);
}
