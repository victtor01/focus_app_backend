package com.focus.app.shared.utils;

import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.domain.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticationUtilsImplements implements AuthenticationUtils {

    @Override
    public UUID getId() {
        User user = getUser();
        return user.getId();
    }

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JpaUserEntity user = (JpaUserEntity) authentication.getPrincipal();
        return UserMapper.toDomain(user);
    }
}

