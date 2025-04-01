package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.outbound.entities.JpaTaskCategoryEntity;
import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTasksCategoriesRepository extends JpaRepository<JpaTaskCategoryEntity, UUID> {
    List<JpaTaskCategoryEntity> findAllByUser(JpaUserEntity user);
    List<JpaTaskCategoryEntity> findAllByIdIn(List<UUID> ids);
}
