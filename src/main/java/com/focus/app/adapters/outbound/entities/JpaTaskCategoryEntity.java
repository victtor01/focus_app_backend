package com.focus.app.adapters.outbound.entities;

import com.focus.app.domain.models.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
@AllArgsConstructor
public class JpaTaskCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private JpaTaskEntity task;
}
