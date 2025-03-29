package com.focus.app.application.ports.out;

import com.focus.app.domain.models.TaskCategory;
import com.focus.app.domain.models.User;

import java.util.List;

public interface TasksCategoriesRepositoryPort {
    TaskCategory save(TaskCategory taskCategory);
    List<TaskCategory> findAllByUser(User user);
}
