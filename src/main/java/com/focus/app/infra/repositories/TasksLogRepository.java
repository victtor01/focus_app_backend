package com.focus.app.infra.repositories;

import com.focus.app.core.dtos.TaskLogDTO;
import com.focus.app.core.models.TaskLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TasksLogRepository extends JpaRepository<TaskLog, UUID> {
    List<TaskLog> findByDayBetweenAndUserId(LocalDate startDate, LocalDate endDate, UUID userId);
}
