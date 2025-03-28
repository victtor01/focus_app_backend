package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.adapters.outbound.entities.JpaTaskLogEntity;
import com.focus.app.adapters.outbound.mappers.TaskLogMapper;
import com.focus.app.adapters.outbound.persistence.jpa.JpaTasksLogRepository;
import com.focus.app.application.ports.out.TasksLogRepositoryPort;
import com.focus.app.domain.models.TaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class TasksLogRepositoryImplements implements TasksLogRepositoryPort {

    private final JpaTasksLogRepository jpaTasksLogRepository;

    @Autowired
    public TasksLogRepositoryImplements(JpaTasksLogRepository tasksLogRepository) {
        this.jpaTasksLogRepository = tasksLogRepository;
    }

    @Override
    public TaskLog save(TaskLog taskLog) {
        JpaTaskLogEntity jpaTaskLogEntity = this.jpaTasksLogRepository.save(TaskLogMapper.toEntity(taskLog));
        return TaskLogMapper.toDomain(jpaTaskLogEntity);
    }

    @Override
    public List<TaskLog> findByDayBetweenAndUserId(LocalDate start, LocalDate end, UUID userId) {
        return this.jpaTasksLogRepository.findByDayBetweenAndUserId(start, end, userId)
            .stream()
            .map(TaskLogMapper::toDomain)
            .toList();
    }

    @Override
    public List<TaskLog> findByDayAndUserId(LocalDate date, UUID userId) {
        return this.jpaTasksLogRepository.findByDayAndUserId(date, userId)
            .stream()
            .map(TaskLogMapper::toDomain)
            .toList();
    }
}
