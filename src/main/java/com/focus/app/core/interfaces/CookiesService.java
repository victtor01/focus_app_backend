package com.focus.app.core.interfaces;

import org.springframework.http.ResponseCookie;

public interface CookiesService {
    ResponseCookie createTokenCookie(String token);
    ResponseCookie createRefreshTokenCookie(String refreshToken);
}
