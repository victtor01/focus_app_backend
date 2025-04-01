package com.focus.app.infrastructure.security;

import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.application.ports.in.CookiesService;
import com.focus.app.application.ports.in.JwtService;
import com.focus.app.application.ports.out.UsersRepositoryPort;
import com.focus.app.domain.models.user.User;
import com.focus.app.shared.utils.AuthenticationClaims;
import com.focus.app.shared.utils.CookiesKeys;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.adapters.outbound.persistence.repositories.UsersRepositoryImplements;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsersRepositoryPort usersRepository;
    private final CookiesService cookiesService;

    @Autowired
    public SecurityFilter(JwtService jwtService, UsersRepositoryImplements usersRepository, CookiesService cookiesService) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
        this.cookiesService = cookiesService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = this.recoverToken(request, CookiesKeys.ACCESS_TOKEN);
        String refreshToken = this.recoverToken(request, CookiesKeys.REFRESH_TOKEN);

        if (accessToken == null || refreshToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isTokenExpired = this.jwtService.isTokenExpired(accessToken);
        boolean isRefreshTokenExpired = this.jwtService.isTokenExpired(refreshToken);
        boolean isSessionExpired = isTokenExpired && isRefreshTokenExpired;

        if (isSessionExpired) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isTokenExpired) {
            accessToken = refreshToken(refreshToken, response);
        }

        Claims claimsOfToken = jwtService.getAllClaims(accessToken);
        String email = (String) claimsOfToken.get(AuthenticationClaims.email);

        User user = usersRepository.findByEmail(email).orElseThrow(
            () -> new BadRequestException("use not found with email: " + email)
        );

        UserDetails userDetails = UserMapper.toEntity(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

    private String refreshToken(String accessToken, HttpServletResponse response) {
        Claims claims = this.jwtService.getAllClaims(accessToken);

        String email = (String) claims.get("email");

        Map<String, String> claimsMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            claimsMap.put(entry.getKey(), String.valueOf(entry.getValue()));
        }

        String newAccessToken = jwtService.generate(claimsMap, email);

        ResponseCookie newAccessTokenCookie = cookiesService.createTokenCookie(newAccessToken);
        response.addHeader(HttpHeaders.SET_COOKIE, newAccessTokenCookie.toString());

        return newAccessToken;
    }


    private String recoverToken(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
