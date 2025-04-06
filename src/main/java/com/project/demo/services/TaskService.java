package com.project.demo.services;

import com.project.demo.models.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService implements ModelTaskService {
    Map<Long, Task> tasks = new HashMap<>();

    public void putTask(Task task){
        tasks.put(task.getIdTask(), task);
    }

    public Task getTask(long key){
        return tasks.get(key);
    }

    public void deleteTask(long key){
        tasks.remove(key);
    }
}
