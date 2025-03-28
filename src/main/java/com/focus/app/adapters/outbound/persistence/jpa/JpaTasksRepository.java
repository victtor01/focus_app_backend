package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.outbound.entities.JpaTaskEntity;
import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTasksRepository extends JpaRepository<JpaTaskEntity, UUID> {
    List<JpaTaskEntity> findAllByUser(JpaUserEntity user);
}
