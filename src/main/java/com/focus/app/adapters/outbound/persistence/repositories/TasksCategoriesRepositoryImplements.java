package com.focus.app.adapters.outbound.persistence.repositories;

import com.focus.app.adapters.outbound.entities.JpaTaskCategoryEntity;
import com.focus.app.adapters.outbound.mappers.TaskCategoryMapper;
import com.focus.app.adapters.outbound.mappers.UserMapper;
import com.focus.app.adapters.outbound.persistence.jpa.JpaTasksCategoriesRepository;
import com.focus.app.application.ports.out.TasksCategoriesRepositoryPort;
import com.focus.app.domain.models.TaskCategory;
import com.focus.app.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TasksCategoriesRepositoryImplements implements TasksCategoriesRepositoryPort {

    private final JpaTasksCategoriesRepository jpaTasksCategoriesRepository;

    @Autowired
    public TasksCategoriesRepositoryImplements(JpaTasksCategoriesRepository jpaTasksCategoriesRepository) {
        this.jpaTasksCategoriesRepository = jpaTasksCategoriesRepository;
    }

    @Override
    public TaskCategory save(TaskCategory taskCategory) {
        JpaTaskCategoryEntity jpaTaskCategoryEntity = TaskCategoryMapper.toEntity(taskCategory);
        JpaTaskCategoryEntity created = this.jpaTasksCategoriesRepository.save(jpaTaskCategoryEntity);
        return TaskCategoryMapper.toDomain(created);
    }

    @Override
    public List<TaskCategory> findAllByUser(User user) {
        return this.jpaTasksCategoriesRepository.findAllByUser(UserMapper.toEntity(user))
            .stream()
            .map(TaskCategoryMapper::toDomain)
            .toList();
    }
}
