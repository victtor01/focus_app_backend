package com.focus.app.adapters.inbound.controllers;

import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.UsersService;
import com.focus.app.domain.models.User;
import com.focus.app.domain.records.CreateUserRecord;
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
    public ResponseEntity create(@Valid @RequestBody() CreateUserRecord createUserRecord) {
        User created = usersService.save(createUserRecord);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("i")
    public ResponseEntity i() {
        UUID id = authenticationUtils.getId();

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
