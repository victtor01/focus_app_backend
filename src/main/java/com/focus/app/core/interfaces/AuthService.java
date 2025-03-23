package com.focus.app.core.interfaces;

import com.focus.app.core.records.TokenRecord;

public interface AuthService {
    TokenRecord auth(String email, String password);
}
