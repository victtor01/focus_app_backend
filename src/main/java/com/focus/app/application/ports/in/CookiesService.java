package com.focus.app.application.ports.in;

import org.springframework.http.ResponseCookie;

public interface CookiesService {
    ResponseCookie createTokenCookie(String token);
    ResponseCookie createRefreshTokenCookie(String refreshToken);
}
