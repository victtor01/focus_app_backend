package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.inbound.dtos.response.ReminderResponse;
import com.focus.app.adapters.outbound.entities.JpaReminderEntity;
import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.domain.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRemindersRepository extends JpaRepository<JpaReminderEntity, UUID> {
    List<JpaReminderEntity> findAllByUser(JpaUserEntity user);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM JpaReminderEntity r WHERE r.id = :id")
    void deleteById(@Param("id") UUID id);
}
