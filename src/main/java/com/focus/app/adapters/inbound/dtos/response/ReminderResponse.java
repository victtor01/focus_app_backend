package com.focus.app.adapters.inbound.dtos.response;

import com.focus.app.domain.enums.ReminderType;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReminderResponse {
    private UUID id;
    private List<LocalDate> customReminderDates;
    private List<LocalDate> reminderDaysOfWeek;
    private List<TaskCategoryResponse> categories;
    private ReminderType reminderType;
}
