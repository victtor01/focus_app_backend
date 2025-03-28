package com.focus.app.application.ports.out;

import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksRepositoryPort {
    Task save(Task task);

    Optional<Task> findById(UUID id);

    List<Task> findAllByUser(User user);

    void delete(Task task);
}
