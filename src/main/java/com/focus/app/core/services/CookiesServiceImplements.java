package com.focus.app.core.services;

import com.focus.app.core.interfaces.CookiesService;
import com.focus.app.core.security.JwtProperties;
import com.focus.app.core.utils.CookiesKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CookiesServiceImplements implements CookiesService {

    private final JwtProperties jwtProperties;

    @Autowired
    public CookiesServiceImplements(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public ResponseCookie createTokenCookie(String token) {
        return ResponseCookie.from(CookiesKeys.ACCESS_TOKEN, token)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(Duration.ofHours(1))
            .build();
    }

    @Override
    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        long cookieLongMaxAgeMinutes = 60 * 60;
        return ResponseCookie.from(CookiesKeys.REFRESH_TOKEN, refreshToken)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(Duration.ofHours(5))
            .build();
    }
}
