package com.focus.app.application.ports.in;

import com.focus.app.application.commands.TokenCommand;

public interface AuthService {
    TokenCommand auth(String email, String password);
}
