package com.project.demo.services;

import com.project.demo.models.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService implements ModelTaskService {
    Map<Long, Task> data = new HashMap<>();

    public void putData(Task task){
        data.put(task.getIdTask(), task);
    }

    public Task getData(long key){
        return data.get(key);
    }

    public void deleteData(long key){
        data.remove(key);
    }
}
