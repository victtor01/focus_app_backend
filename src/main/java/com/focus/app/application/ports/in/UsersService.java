package com.focus.app.application.ports.in;

import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateUserRecord;

public interface UsersService {
    User save(CreateUserRecord createUserRecord);
}
