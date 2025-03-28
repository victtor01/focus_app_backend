package com.focus.app.application.ports.in;

import com.focus.app.domain.records.TokenRecord;

public interface AuthService {
    TokenRecord auth(String email, String password);
}
