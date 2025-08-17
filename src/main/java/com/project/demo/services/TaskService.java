package com.project.demo.services;

import com.project.demo.db.Task;
import org.springframework.kafka.core.KafkaTemplate;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.db.repositories.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;

@Service
public class TaskService implements ModelTaskService {
    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TaskService(TaskRepository taskRepo,
                       UserRepository userRepo,
                       KafkaTemplate<String, String> kafkaTemplate) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @CacheEvict(value = {"tasksByUser", "tasksAll"}, allEntries = true)
    public void insertTask(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate) {
        if (!userRepo.existsByUserId(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
        if (targetDate.toInstant().isBefore(ZonedDateTime.now().toInstant())) {
            throw new IllegalArgumentException("Target date must be in the future.");
        }

        ZonedDateTime utcTargetDate = targetDate.withZoneSameInstant(ZoneOffset.UTC);

        taskRepo.insert(taskId, userId, taskValue, utcTargetDate);

        String event = String.format("{\"taskId\":%d,\"userId\":%d,\"event\":\"created\"}", taskId, userId);
        kafkaTemplate.send("task-events", event);
    }

    @Override
    @CacheEvict(value = {"tasksByUser", "tasksAll"}, allEntries = true)
    public void deleteTask(Long userId, Long taskId) {
        if (!userRepo.existsByUserId(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }

        taskRepo.delete(userId, taskId);

        String event = String.format("{\"taskId\":%d,\"userId\":%d,\"event\":\"deleted\"}", taskId, userId);
        kafkaTemplate.send("task-events", event);
    }

    @Override
    @Cacheable(value = "tasksByUser", key = "#root.methodName")
    public Collection<Task> getTasks() {
        return taskRepo.findAllAndDeletedFalse();
    }

    @Override
    @Cacheable(value = "tasksAll")
    public Collection<Task> getAllTasks() {
        return taskRepo.findAllAndDeletedFalse();
    }
}
