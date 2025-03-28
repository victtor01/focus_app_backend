package com.focus.app.adapters.outbound.persistence.jpa;

import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {
    Optional<JpaUserEntity> findByEmail(String email);
}
