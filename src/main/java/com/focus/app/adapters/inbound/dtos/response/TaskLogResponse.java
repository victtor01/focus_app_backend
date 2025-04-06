package com.focus.app.adapters.inbound.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskLogResponse {
    private UUID id;
    private LocalDate day;
    private LocalTime hour;
    private ReminderResponse reminder;
    private TaskResponse task;
}
