package com.project.demo.services;

import com.project.demo.db.Task;

import java.time.ZonedDateTime;
import java.util.Collection;

public interface ModelTaskService {
    public void insertTask(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate);
    public Collection<Task> getTasks();
    public Collection<Task> getAllTasks();
    public void deleteTask(Long userId, Long taskId);
}