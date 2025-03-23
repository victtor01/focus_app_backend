package com.focus.app.core.interfaces;

import com.focus.app.core.dtos.TaskDTO;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskRecord;

import java.util.List;
import java.util.UUID;

public interface TasksService {
    Task create(User user, CreateTaskRecord createTaskRecord);
    void delete(UUID taskId, User user);
    List<TaskDTO> findByUser(User user);
}
