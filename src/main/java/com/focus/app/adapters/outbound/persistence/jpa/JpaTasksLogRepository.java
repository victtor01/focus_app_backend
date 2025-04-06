package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.outbound.entities.JpaTaskLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaTasksLogRepository extends JpaRepository<JpaTaskLogEntity, UUID> {
    List<JpaTaskLogEntity> findByDayBetweenAndUserId(LocalDate startDate, LocalDate endDate, UUID userId);
    List<JpaTaskLogEntity> findByDayAndUserId(LocalDate day, UUID userId);

    @Override
    @Modifying
    @Transactional
    @Query("DELETE FROM JpaTaskLogEntity r WHERE r.id = :id")
    void deleteById(@Param("id") UUID id);
}
