package com.focus.app.application.ports.in;

import com.focus.app.adapters.inbound.dtos.TaskDTO;
import com.focus.app.application.commands.CreateTaskCommand;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface TasksService {
    Task create(User user, CreateTaskCommand createTaskRecord);

    void delete(UUID taskId, User user);

    List<Task> findAllByUser(User user);
}
