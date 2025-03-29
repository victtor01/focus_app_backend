package com.focus.app.services;

import com.focus.app.application.commands.CreateTaskCommand;
import com.focus.app.domain.models.User;
import com.focus.app.application.services.TasksServiceImplements;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.adapters.outbound.persistence.jpa.JpaTasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TasksServicesTests {

    @Mock
    private JpaTasksRepository tasksRepository;

    @InjectMocks
    private TasksServiceImplements tasksService;

    private User user;

    private CreateTaskCommand createTaskRecord;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = mock(User.class);
    }

    @Test
    void testCreateTaskWithInvalidColor() {
        createTaskRecord = new CreateTaskCommand("test", "test", "INVALID", List.of());

        assertThrows(BadRequestException.class, () -> {
            tasksService.create(user, createTaskRecord);
        });
    }

}
