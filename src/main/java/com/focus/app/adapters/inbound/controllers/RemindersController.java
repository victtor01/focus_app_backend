package com.focus.app.adapters.inbound.controllers;

import com.focus.app.adapters.inbound.dtos.response.ReminderResponse;
import com.focus.app.adapters.inbound.dtos.request.CreateReminderRequest;
import com.focus.app.adapters.inbound.mappers.ReminderMapper;
import com.focus.app.application.commands.CreateReminderCommand;
import com.focus.app.application.ports.in.AuthenticationUtils;
import com.focus.app.application.ports.in.RemindersCalendar;
import com.focus.app.application.ports.in.RemindersService;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reminders")
public class RemindersController {

    private final RemindersService remindersService;
    private final AuthenticationUtils authenticationUtils;
    private final RemindersCalendar remindersCalendar;

    @Autowired
    public RemindersController(RemindersService remindersService, AuthenticationUtils authenticationUtils, RemindersCalendar remindersCalendar) {
        this.remindersService = remindersService;
        this.authenticationUtils = authenticationUtils;
        this.remindersCalendar = remindersCalendar;
    }

    @PostMapping
    public ResponseEntity<ReminderResponse> create(@RequestBody @Valid CreateReminderRequest createReminderRecord) {
        User user = authenticationUtils.getUser();

        CreateReminderCommand createReminderCommand = new CreateReminderCommand(
            createReminderRecord.taskId(),
            createReminderRecord.customReminderDates(),
            createReminderRecord.reminderDaysOfWeek(),
            createReminderRecord.reminderType(),
            createReminderRecord.repeat()
        );

        Reminder reminder = this.remindersService.save(user, createReminderCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(ReminderMapper.toResponse(reminder));
    }

    @GetMapping
    public ResponseEntity<List<ReminderResponse>> findAll() {
        User user = authenticationUtils.getUser();

        List<ReminderResponse> reminders = this.remindersService.findAllByUser(user).stream()
            .map(ReminderMapper::toResponse).toList();

        return ResponseEntity.status(HttpStatus.OK).body(reminders);
    }

    @GetMapping("/calendar")
    public ResponseEntity<Map<LocalDate, List<ReminderResponse>>> getCalendar(
        @RequestParam LocalDate start,
        @RequestParam LocalDate end
    ) {
        UUID userId = authenticationUtils.getId();

        Map<LocalDate, List<Reminder>> calendar = this.remindersCalendar.createRemindersCalendar(userId, start, end);

        Map<LocalDate, List<ReminderResponse>> responseCalendar = calendar.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ReminderMapper::toResponse)
                    .collect(Collectors.toList()),
                (a, b) -> b,
                LinkedHashMap::new
            ));

        return ResponseEntity.ok(responseCalendar);
    }

    @GetMapping("{reminderId}")
    public ResponseEntity<ReminderResponse> findById(@PathVariable UUID reminderId) {
        UUID userId = authenticationUtils.getId();
        Reminder reminder = this.remindersService.findByIdAndUser(reminderId, userId);

        return ResponseEntity.ok(ReminderMapper.toResponse(reminder));
    }

    @DeleteMapping("{reminderId}")
    public ResponseEntity<String> delete(@PathVariable UUID reminderId) {
        User user = authenticationUtils.getUser();

        this.remindersService.delete(user, reminderId);

        return ResponseEntity.ok("reminder deleted");
    }
}
