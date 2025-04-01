package com.focus.app.application.ports.out;

import com.focus.app.domain.models.taskCategory.TaskCategory;
import com.focus.app.domain.models.user.User;

import java.util.List;
import java.util.UUID;

public interface TasksCategoriesRepositoryPort {
    TaskCategory save(TaskCategory taskCategory);
    List<TaskCategory> findAllByUser(User user);
    List<TaskCategory> findAllByIds(List<UUID> ids);
}
