package com.focus.app.adapters.outbound.entities;

import com.focus.app.domain.models.Task;
import com.focus.app.domain.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tasks_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JpaTaskLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDate day;

    @Column()
    private LocalTime hour;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JpaUserEntity user;

    @ManyToOne()
    @JoinColumn(name = "task_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JpaTaskEntity task;
}

