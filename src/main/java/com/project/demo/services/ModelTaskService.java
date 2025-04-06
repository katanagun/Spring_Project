package com.project.demo.services;

import com.project.demo.models.Task;

public interface ModelTaskService {
    public void putTask(Task task);
    public Task getTask(long key);
    public void deleteTask(long key);
}
