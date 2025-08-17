package com.project.demo.db.repositories;

import com.project.demo.db.Task;

import java.time.ZonedDateTime;
import java.util.Collection;

public interface TaskRepository {
    void insert(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate);
    Collection<Task> findAllAndDeletedFalse();
    void delete(Long userId, Long taskId);
}
