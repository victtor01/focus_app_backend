package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.adapters.outbound.persistence.jpa.JpaUserRepository;
import com.focus.app.application.ports.out.UsersRepositoryPort;
import com.focus.app.domain.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsersRepositoryImplements implements UsersRepositoryPort {
    private final JpaUserRepository jpaUserRepository;

    public UsersRepositoryImplements (JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        JpaUserEntity jpaUserEntity = UserMapper.toEntity(user);
        JpaUserEntity userEntity = this.jpaUserRepository.save(jpaUserEntity);
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
       return this.jpaUserRepository.findByEmail(email).map(UserMapper::toDomain);
    }
}