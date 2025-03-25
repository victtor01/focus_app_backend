package com.focus.app.core.interfaces;

import com.focus.app.core.dtos.TaskLogDTO;
import com.focus.app.core.models.TaskLog;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateTaskLogRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TasksLogService {
    TaskLog create(CreateTaskLogRecord createTaskLogRecord, User user);
    Map<LocalDate, List<TaskLogDTO>> getCalendar(LocalDate start, LocalDate end);
}
