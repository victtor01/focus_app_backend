package com.focus.app.infra.controllers;

import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.interfaces.UsersService;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateUserRecord;
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
        User created = usersService.Create(createUserRecord);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("i")
    public ResponseEntity i() {
        UUID id = authenticationUtils.getId();

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
