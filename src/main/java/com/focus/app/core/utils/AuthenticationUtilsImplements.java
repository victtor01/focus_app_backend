package com.focus.app.core.utils;

import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.models.User;
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
        User user = (User) authentication.getPrincipal();
        return user;
    }
}

