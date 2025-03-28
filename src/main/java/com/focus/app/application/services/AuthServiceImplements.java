package com.focus.app.application.services;

import com.focus.app.application.ports.in.AuthService;
import com.focus.app.application.ports.in.JwtService;
import com.focus.app.domain.records.TokenRecord;
import com.focus.app.shared.utils.AuthenticationClaims;
import com.focus.app.shared.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImplements implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthServiceImplements(
        AuthenticationManager authenticationManager,
        JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public TokenRecord auth(String email, String password) throws RuntimeException {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            var auth = authenticationManager.authenticate(authenticationToken);

            Map<String, String> claims = new HashMap<>();

            claims.put(AuthenticationClaims.role, auth.getAuthorities()
                .stream()
                .map(data -> data.getAuthority())
                .collect(Collectors.joining(",")));

            claims.put(AuthenticationClaims.email, auth.getName());

            String accessToken = jwtService.generate(claims, auth.getName());
            String refreshToken = this.jwtService.generateLong(claims, auth.getName());

            return new TokenRecord(accessToken, refreshToken);
        } catch (Exception e) {
            throw new BadRequestException("email or password incorrect");
        }

    }
}
