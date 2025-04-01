package com.focus.app.application.ports.in;

import com.focus.app.domain.models.user.User;

import java.util.UUID;

public interface AuthenticationUtils {
    UUID getId();
    User getUser();
}

