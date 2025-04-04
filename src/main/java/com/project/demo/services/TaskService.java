package com.project.demo.services;

import com.project.demo.Exceptions.NotificationNotFoundException;
import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import com.project.demo.db.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskService implements ModelTaskService {
    private final TaskRepository taskRepo;
    private final NotificationRepository notificationRepo;
    private final UserRepository userRepo;

    public TaskService(TaskRepository taskRepo, NotificationRepository notificationRepo, UserRepository userRepo) {
        this.taskRepo = taskRepo;
        this.notificationRepo = notificationRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void insertTask(Long taskId, Long userId, String taskValue, ZonedDateTime targetDate) {
        if (!userRepo.existsByUserId(userId)) {
            throw new IllegalArgumentException("User with id " + userId + " not found.");
        }
        taskRepo.insert(taskId, userId, taskValue, targetDate);

        Notification notification = new Notification(taskId, userId, taskId, "created");

        notificationRepo.saveNotification(notification);
    }

    @Override
    public Collection<Task> getTasks() {
        return taskRepo.findAllAndDeletedFalse().stream()
                .filter(task -> task.getCreationDate().isBefore(task.getTargetDate()))
                .filter(task -> {
                    Notification n = notificationRepo.findByUserId(task.getUserId()).stream()
                            .filter(notif -> notif.getTaskId().equals(task.getTaskId()))
                            .findFirst().orElse(null);
                    return n == null || !"deleted".equals(n.getNotificationValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Task> getAllTasks() {
        return taskRepo.findAllAndDeletedFalse().stream()
                .filter(task -> {
                    Notification n = notificationRepo.findByUserId(task.getUserId()).stream()
                            .filter(notif -> notif.getTaskId().equals(task.getTaskId()))
                            .findFirst().orElse(null);
                    return n == null || !"deleted".equals(n.getNotificationValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long userId, Long taskId) {
        Notification existing = notificationRepo.findByUserId(userId).stream()
                .filter(n -> Objects.equals(n.getTaskId(), taskId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setNotificationValue("deleted");
            notificationRepo.saveNotification(existing);
        } else {
            throw new NotificationNotFoundException(taskId, userId);
        }
    }

}