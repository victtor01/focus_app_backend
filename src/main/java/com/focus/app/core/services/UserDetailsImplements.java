package com.focus.app.core.services;

import com.focus.app.infra.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplements implements UserDetailsService {

    @Autowired
    private UsersRepository _usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = _usersRepository.findByEmail(email);
        return user.orElseThrow();
    }
}
