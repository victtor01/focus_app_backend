package com.focus.app.adapters.outbound.mappers;

import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.domain.models.User;

public class UserMapper {
    public static JpaUserEntity toEntity(User user) {
        if(user == null) return null;

        return JpaUserEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .email(user.getEmail())
            .build();
    }

    public static User toDomain(JpaUserEntity entity) {
        if(entity == null) return null;

        return new User(
            entity.getId(),
            entity.getUsername(),
            entity.getEmail(),
            entity.getPassword()
        );
    }
}
