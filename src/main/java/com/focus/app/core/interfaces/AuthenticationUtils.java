package com.focus.app.core.interfaces;

import com.focus.app.core.models.User;

import java.util.UUID;

public interface AuthenticationUtils {
    UUID getId();
    User getUser();
}

