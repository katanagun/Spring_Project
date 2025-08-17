package com.project.demo.db.JPA;

import com.project.demo.db.Task;
import com.project.demo.db.repositories.TaskRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
@Profile("JPA")
public class TaskJpaAdapter implements TaskRepository {

    private final TaskJpaRepository jpaRepository;

    public TaskJpaAdapter(TaskJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void insert(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate) {
        Task task = new Task();
        task.setTaskId(taskId);
        task.setUserId(userId);
        task.setTaskValue(taskValue);
        task.setCreationDate(ZonedDateTime.now());
        task.setTargetDate(targetDate);
        jpaRepository.save(task);
    }

    @Override
    public Collection<Task> findAllAndDeletedFalse() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Long userId, Long taskId) {
        Task task = jpaRepository.findById(taskId).orElse(null);
        if (task != null && task.getUserId().equals(userId)) {
            jpaRepository.delete(task);
        }
    }

}
