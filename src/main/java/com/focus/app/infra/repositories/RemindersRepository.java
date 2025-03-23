package com.focus.app.infra.repositories;

import com.focus.app.core.dtos.ReminderDTO;
import com.focus.app.core.models.Reminder;
import com.focus.app.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RemindersRepository extends JpaRepository<Reminder, UUID> {
    List<ReminderDTO> findAllByUser(User user);
}
