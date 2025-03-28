package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.domain.models.Reminder;
import com.focus.app.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasksRemindersRepositoryImplements implements RemindersRepositoryPort {

    @Override
    public List<Reminder> findAllByUser(User user) {
        return List.of();
    }

    @Override
    public Reminder save(Reminder reminder) {
        return null;
    }
}
