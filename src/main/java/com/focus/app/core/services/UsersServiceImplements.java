package com.focus.app.core.services;


import com.focus.app.core.interfaces.UsersService;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateUserRecord;
import com.focus.app.core.utils.EmailAddress;
import com.focus.app.infra.config.errors.BadRequestException;
import com.focus.app.infra.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImplements implements UsersService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImplements(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public User Create(CreateUserRecord createUserRecord) {
        User user = new User();

        EmailAddress address = new EmailAddress(createUserRecord.email());
        Optional<User> userIn = usersRepository.findByEmail(createUserRecord.email());

        if (userIn.isPresent()) {
            throw new BadRequestException("this email is used");
        }

        String password = passwordEncoder.encode(createUserRecord.password());

        user.setUsername(createUserRecord.username());
        user.setEmail(address.getEmail());
        user.setPassword(password);

        usersRepository.save(user);

        return user;
    }
}
