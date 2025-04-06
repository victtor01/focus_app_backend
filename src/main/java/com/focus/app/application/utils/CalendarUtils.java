package com.focus.app.application.utils;

import com.focus.app.adapters.inbound.dtos.TaskLogDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CalendarUtils {
    public static <T> Map<LocalDate, List<T>> populateBetween(LocalDate start, LocalDate end) {
        return start.datesUntil(end.plusDays(1))
            .collect(Collectors.toMap(
                day -> day,
                day -> new ArrayList<>(),
                (a, b) -> b,
                TreeMap::new // Ordem natural crescente!
            ));
    }
}
