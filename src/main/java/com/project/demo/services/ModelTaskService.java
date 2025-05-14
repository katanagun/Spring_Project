package com.project.demo.services;

import com.project.demo.models.Task;

import java.util.Collection;

public interface ModelTaskService {
    public void putTask(Task task, long idUser);
    public Collection<Task> getTask();
    public Collection<Task> getAllTasks();
    public void deleteTask(long idUser, long idTask, String notification);
}