package com.focus.app.application.utils;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class WeekValidator {

    public static boolean areDatesInSameWeek(List<LocalDate> dates) {
        if (dates == null || dates.isEmpty()) {
            return true;
        }

        LocalDate referenceDate = dates.getFirst();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        int referenceWeek = referenceDate.get(weekFields.weekOfWeekBasedYear());
        int referenceYear = referenceDate.get(weekFields.weekBasedYear());

        return dates.stream().allMatch(date ->
            date.get(weekFields.weekOfWeekBasedYear()) == referenceWeek &&
                date.get(weekFields.weekBasedYear()) == referenceYear
        );
    }

    public static boolean areDatesInSameMonth(List<LocalDate> dates) {
        if (dates == null || dates.isEmpty()) return true;

        LocalDate referenceDate = dates.getFirst();
        int referenceMonth = referenceDate.getMonthValue();
        int referenceYear = referenceDate.getYear();

        return dates.stream().allMatch(date ->
            date.getMonthValue() == referenceMonth && date.getYear() == referenceYear
        );
    }
}
