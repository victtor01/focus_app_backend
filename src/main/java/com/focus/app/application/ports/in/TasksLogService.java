package com.focus.app.application.ports.in;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;
import com.focus.app.domain.models.TaskLog;
import com.focus.app.domain.models.User;
import com.focus.app.application.commands.CreateTaskLogCommand;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TasksLogService {
    TaskLog create(CreateTaskLogCommand createTaskLogRecord, User user);

    Map<LocalDate, List<TaskLogDTO>> getCalendar(LocalDate start, LocalDate end);

    List<TaskLogDTO> findAllByDate(LocalDate dateT);
}
