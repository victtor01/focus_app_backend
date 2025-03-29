package com.focus.app.application.ports.in;

import com.focus.app.domain.models.User;
import com.focus.app.application.commands.CreateUserCommand;

public interface UsersService {
    User save(CreateUserCommand createUserRecord);
}
