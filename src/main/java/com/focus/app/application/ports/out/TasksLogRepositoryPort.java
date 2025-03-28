package com.focus.app.application.ports.out;

import com.focus.app.domain.models.TaskLog;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TasksLogRepositoryPort {
    TaskLog save(TaskLog taskLog);
    List<TaskLog> findByDayBetweenAndUserId(LocalDate start, LocalDate end, UUID userId);
    List<TaskLog> findByDayAndUserId(LocalDate date, UUID userId);
}

