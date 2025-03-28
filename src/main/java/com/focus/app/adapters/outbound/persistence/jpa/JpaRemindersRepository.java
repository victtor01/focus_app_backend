package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.inbound.dtos.ReminderDTO;
import com.focus.app.adapters.outbound.entities.JpaReminderEntity;
import com.focus.app.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRemindersRepository extends JpaRepository<JpaReminderEntity, UUID> {
    List<ReminderDTO> findAllByUser(User user);

}
