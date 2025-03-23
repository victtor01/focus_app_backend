package com.focus.app.core.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.DayOfWeek;

@Getter
@RequiredArgsConstructor
public enum ReminderDay {
    MONDAY(DayOfWeek.MONDAY),
    TUESDAY(DayOfWeek.TUESDAY),
    WEDNESDAY(DayOfWeek.WEDNESDAY),
    THURSDAY(DayOfWeek.THURSDAY),
    FRIDAY(DayOfWeek.FRIDAY),
    SATURDAY(DayOfWeek.SATURDAY),
    SUNDAY(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;
}