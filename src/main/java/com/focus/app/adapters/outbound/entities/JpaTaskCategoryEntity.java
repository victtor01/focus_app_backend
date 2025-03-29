package com.focus.app.adapters.outbound.entities;

import com.focus.app.domain.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class JpaTaskCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToMany
    private List<JpaTaskEntity> tasks;

    @ManyToOne
    private JpaUserEntity user;
}
