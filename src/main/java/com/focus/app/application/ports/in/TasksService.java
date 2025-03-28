package com.focus.app.application.ports.in;

import com.focus.app.adapters.inbound.dtos.TaskDTO;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateTaskRecord;

import java.util.List;
import java.util.UUID;

public interface TasksService {
    Task create(User user, CreateTaskRecord createTaskRecord);

    void delete(UUID taskId, User user);

    List<TaskDTO> findAllByUser(User user);
}
