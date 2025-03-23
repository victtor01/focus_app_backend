package com.focus.app.infra.repositories;

import com.focus.app.core.dtos.TaskDTO;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TasksRepository extends JpaRepository<Task, UUID> {
    List<TaskDTO> findAllByUser(User user);
}
