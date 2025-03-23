package com.focus.app.core.interfaces;

import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateUserRecord;

public interface UsersService {
    User Create(CreateUserRecord createUserRecord);
}
