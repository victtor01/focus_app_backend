package com.focus.app.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TaskCategory {
    private UUID id;

    private String name;

    private String color;

    private Task task;
}
