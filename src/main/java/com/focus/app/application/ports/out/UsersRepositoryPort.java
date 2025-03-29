package com.focus.app.application.ports.out;

import com.focus.app.domain.models.User;

import java.util.Optional;

public interface UsersRepositoryPort {
    User save(User user);

    Optional<User> findByEmail(String email);
}
