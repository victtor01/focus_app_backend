package com.focus.app.application.ports.in;

import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.domain.models.User;

import java.util.UUID;

public interface AuthenticationUtils {
    UUID getId();
    User getUser();
}

