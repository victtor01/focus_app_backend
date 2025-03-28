package com.focus.app.services;

import com.focus.app.domain.enums.ReminderDay;
import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateReminderRecord;
import com.focus.app.application.services.RemindersServiceImplements;
import com.focus.app.shared.exceptions.NotFoundException;
import com.focus.app.adapters.outbound.persistence.jpa.JpaRemindersRepository;
import com.focus.app.adapters.outbound.persistence.jpa.JpaTasksRepository;
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
    private JpaTasksRepository tasksRepository;

    @Mock
    private JpaRemindersRepository remindersRepository;

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
