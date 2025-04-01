package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.response.ReminderResponse;
import com.focus.app.adapters.inbound.dtos.request.CreateReminderRequest;
import com.focus.app.adapters.inbound.mappers.ReminderMapper;
import com.focus.app.application.commands.CreateReminderCommand;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.RemindersService;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;
import jakarta.validation.Valid;
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
    public ResponseEntity<ReminderResponse> create(@RequestBody @Valid CreateReminderRequest createReminderRecord) {
        User user = authenticationUtils.getUser();

        CreateReminderCommand createReminderCommand = new CreateReminderCommand(
            createReminderRecord.taskId(),
            createReminderRecord.customReminderDates(),
            createReminderRecord.reminderDaysOfWeek(),
            createReminderRecord.reminderType()
        );

        Reminder reminder = this.remindersService.save(user, createReminderCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(ReminderMapper.toResponse(reminder));
    }

    @GetMapping
    public ResponseEntity<List<ReminderResponse>> findAll() {
        User user = authenticationUtils.getUser();
        List<Reminder> reminders = this.remindersService.findAllByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(reminders.stream().map(ReminderMapper::toResponse).toList());
    }

}
