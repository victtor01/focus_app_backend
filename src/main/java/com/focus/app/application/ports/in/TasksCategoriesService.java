package com.focus.app.application.ports.in;


import com.focus.app.application.commands.CreateTaskCategoryCommand;
import com.focus.app.domain.models.TaskCategory;
import com.focus.app.domain.models.User;

import java.util.List;

public interface TasksCategoriesService {
    TaskCategory create(CreateTaskCategoryCommand createTaskCategoryRecord, User user);
    List<TaskCategory> findAllByUser(User user);
}
