package com.project.demo.services;

import com.project.demo.models.Task;

import java.time.ZonedDateTime;
import java.util.Collection;

public interface ModelTaskService {
    public void insertTask(long idTask, long idUser, String valueTask, ZonedDateTime targetDate);
    public Collection<Task> getTasks();
    public Collection<Task> getAllTasks();
    public void deleteTask(long idUser, long idTask, String notification);
}
