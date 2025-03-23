package com.focus.app.infra.controllers;

import com.focus.app.core.interfaces.AuthService;
import com.focus.app.core.interfaces.CookiesService;
import com.focus.app.core.records.AuthRecord;
import com.focus.app.core.records.TokenRecord;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final CookiesService cookiesService;

    public AuthController(AuthService authService, CookiesService cookiesService) {
        this.authService = authService;
        this.cookiesService = cookiesService;
    }

    @PostMapping
    public ResponseEntity auth(@RequestBody @Valid AuthRecord authRecord) {
        TokenRecord tokens = this.authService.auth(authRecord.email(), authRecord.password());

        ResponseCookie accessTokenCookie = cookiesService.createTokenCookie(tokens.accessToken());
        ResponseCookie refreshTokenCookie = cookiesService.createRefreshTokenCookie(tokens.refreshToken());

        return ResponseEntity.status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
            .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
            .body(tokens);
    }
}
