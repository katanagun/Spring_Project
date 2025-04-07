package com.project.demo.services;

import com.project.demo.db.repository.TaskRep;
import com.project.demo.db.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService implements ModelTaskService {
    @Autowired
    private TaskRep taskRep;

    public void putTask(Task task){
        taskRep.save(task);
    }

    public Task getTask(long idNotification){
        Optional<Task> taskOptional = taskRep.findById(idNotification);
        return taskOptional.orElse(null);
    }

    public void deleteTask(long key){
        taskRep.deleteById(key);
    }
}
