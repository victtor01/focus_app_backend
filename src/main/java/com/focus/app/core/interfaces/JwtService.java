package com.focus.app.core.interfaces;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtService {
    String generate(Map<String, String> claims, String username, long expireInterval);
    String generate(Map<String, String> claims, String username);
    String generateLong(Map<String, String> claims, String username);
    boolean isTokenExpired(String token);
    Claims getAllClaims(String token);
}
