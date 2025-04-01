package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.adapters.outbound.entities.JpaTaskEntity;
import com.focus.app.adapters.outbound.mappers.TaskMapper;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.adapters.outbound.persistence.jpa.JpaTasksRepository;
import com.focus.app.application.ports.out.TasksRepositoryPort;
import com.focus.app.domain.models.task.Task;
import com.focus.app.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TasksRepositoryImplements implements TasksRepositoryPort {

    private final JpaTasksRepository tasksRepository;

    @Autowired
    public TasksRepositoryImplements(JpaTasksRepository jpaTasksRepository) {
        this.tasksRepository = jpaTasksRepository;
    }

    @Override
    public Task save(Task task) {
        JpaTaskEntity toCreate = TaskMapper.toEntity(task);
        JpaTaskEntity taskEntity = this.tasksRepository.save(toCreate);
        return TaskMapper.toDomain(taskEntity);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return this.tasksRepository.findById(id).map(TaskMapper::toDomain);
    }

    @Override
    public List<Task> findAllByUser(User user) {
        return this.tasksRepository.findAllByUser(UserMapper.toEntity(user))
            .stream()
            .map(TaskMapper::toDomain)
            .toList();
    }

    @Override
    public void delete(Task task) {
        this.tasksRepository.delete(TaskMapper.toEntity(task));
    }
}
