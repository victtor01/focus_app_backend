package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.adapters.outbound.entities.JpaReminderEntity;
import com.focus.app.adapters.outbound.entities.JpaUserEntity;
import com.focus.app.adapters.outbound.mappers.ReminderMapper;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.adapters.outbound.persistence.jpa.JpaRemindersRepository;
import com.focus.app.application.ports.out.RemindersRepositoryPort;
import com.focus.app.domain.models.reminders.Reminder;
import com.focus.app.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasksRemindersRepositoryImplements implements RemindersRepositoryPort {

    private final JpaRemindersRepository jpaRemindersRepository;

    @Autowired
    public TasksRemindersRepositoryImplements(JpaRemindersRepository jpaTasksRepository) {
        this.jpaRemindersRepository = jpaTasksRepository;
    }

    @Override
    public List<Reminder> findAllByUser(User user) {
        JpaUserEntity userEntity = UserMapper.toEntity(user);
        return this.jpaRemindersRepository.findAllByUser(userEntity)
            .stream()
            .map(ReminderMapper::toDomain)
            .toList();
    }

    @Override
    public Reminder save(Reminder reminder) {
        JpaReminderEntity jpaReminderEntity = ReminderMapper.toEntity(reminder);
        JpaReminderEntity created = this.jpaRemindersRepository.save(jpaReminderEntity);
        return ReminderMapper.toDomain(created);
    }
}
