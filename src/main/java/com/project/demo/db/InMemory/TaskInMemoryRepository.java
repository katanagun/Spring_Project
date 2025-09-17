package com.project.demo.db.InMemory;

import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.TaskRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("InMemory")
public class TaskInMemoryRepository implements TaskRepository {
    public Map<Long, Task> tasks = new HashMap<>();

    @Override
    public void insert(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate){
        tasks.put(taskId, new Task(taskId, userId, taskValue, ZonedDateTime.now(), targetDate));
    }

    @Override
    public Collection<Task> findAllAndDeletedFalse() {
        return tasks.values();
    }

    @Override
    public void delete(Long userId, Long taskId) {
        Task task = tasks.get(taskId);
        if (task != null && Objects.equals(task.getUserId(), userId)) {
            tasks.remove(taskId);
        }
    }

}
