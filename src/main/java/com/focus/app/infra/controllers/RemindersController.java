package com.focus.app.infra.controllers;

import com.focus.app.core.dtos.ReminderDTO;
import com.focus.app.core.interfaces.AuthenticationUtils;
import com.focus.app.core.interfaces.RemindersService;
import com.focus.app.core.models.User;
import com.focus.app.core.records.CreateReminderRecord;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class RemindersController {

    private final RemindersService remindersService;
    private final AuthenticationUtils authenticationUtils;

    @Autowired
    public RemindersController(RemindersService remindersService, AuthenticationUtils authenticationUtils) {
        this.remindersService = remindersService;
        this.authenticationUtils = authenticationUtils;
    }


    @PostMapping
    public ResponseEntity<ReminderDTO> create(@RequestBody @Valid CreateReminderRecord createReminderRecord) {
        User user = authenticationUtils.getUser();

        ReminderDTO reminderDTO = this.remindersService.create(user, createReminderRecord);

        return ResponseEntity.status(HttpStatus.CREATED).body(reminderDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> findAll() {
        User user = authenticationUtils.getUser();
        List<ReminderDTO> reminderDTOS = this.remindersService.findAllByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(reminderDTOS);
    }

}
