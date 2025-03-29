package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.request.CreateUserRequest;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.UsersService;
import com.focus.app.application.commands.CreateUserCommand;
import com.focus.app.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public UsersController(UsersService usersService, AuthenticationUtils authenticationUtils) {
        this.usersService = usersService;
        this.authenticationUtils = authenticationUtils;
    }

    @PostMapping()
    public ResponseEntity<User> create(@Valid @RequestBody() CreateUserRequest createUserRequest) {

        User created = usersService.save(
            new CreateUserCommand(
                createUserRequest.username(),
                createUserRequest.email(),
                createUserRequest.password()
            )
        );

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("i")
    public ResponseEntity<UUID> i() {
        UUID id = authenticationUtils.getId();

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
