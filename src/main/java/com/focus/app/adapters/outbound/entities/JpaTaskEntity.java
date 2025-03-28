package com.focus.app.adapters.outbound.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

@Data
@Table(name = "tasks")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Length(max = 100, min = 1)
    private String name;

    @Column
    @Length(max = 200)
    private String description;

    @Column
    @Length(max = 30)
    private String color;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JpaReminderEntity> reminders;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private JpaUserEntity user;

    @OneToMany()
    private List<JpaTaskCategoryEntity> categories;
}
