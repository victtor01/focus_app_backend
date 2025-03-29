package com.focus.app.application.services;


import com.focus.app.application.ports.in.UsersService;
import com.focus.app.domain.models.User;
import com.focus.app.application.commands.CreateUserCommand;
import com.focus.app.shared.utils.EmailAddress;
import com.focus.app.shared.exceptions.BadRequestException;
import com.focus.app.adapters.outbound.persistence.repositories.UsersRepositoryImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImplements implements UsersService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepositoryImplements usersRepository;

    @Autowired
    public UsersServiceImplements(PasswordEncoder passwordEncoder, UsersRepositoryImplements usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public User save(CreateUserCommand createUserRecord) {
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
