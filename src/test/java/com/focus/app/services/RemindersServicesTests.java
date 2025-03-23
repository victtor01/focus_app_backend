package com.focus.app.services;

import com.focus.app.core.enums.ReminderDay;
import com.focus.app.core.interfaces.RemindersService;
import com.focus.app.core.models.Task;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateReminderRecord;
import com.focus.app.core.services.RemindersServiceImplements;
import com.focus.app.infra.config.errors.NotFoundException;
import com.focus.app.infra.repositories.RemindersRepository;
import com.focus.app.infra.repositories.TasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class RemindersServicesTests {

    @Mock
    private TasksRepository tasksRepository;

    @Mock
    private RemindersRepository remindersRepository;

    @InjectMocks
    private RemindersServiceImplements remindersService;

    private User user;
    private Task task;

    private CreateReminderRecord createReminderRecord;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldThrowErrorBecauseTaskNotFound() {
        CreateReminderRecord createReminderRecord = new CreateReminderRecord(
            UUID.randomUUID(),
            LocalDateTime.now(),
            List.of(ReminderDay.FRIDAY)
        );

        when(tasksRepository.findById(createReminderRecord.taskId())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            remindersService.create(user, createReminderRecord);
        });

        assertEquals("task to add reminder not found", exception.getMessage());
    }
}
