package com.focus.app.application.services;

import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.application.ports.out.UsersRepositoryPort;
import com.focus.app.shared.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplements implements UserDetailsService {

    @Autowired
    private UsersRepositoryPort _usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return _usersRepository.findByEmail(email).map(UserMapper::toEntity).orElseThrow(
            () -> new BadRequestException("essa bagaça passou aqui")
        );
    }
}
