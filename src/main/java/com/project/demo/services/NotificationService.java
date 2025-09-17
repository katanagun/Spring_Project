package com.project.demo.services;

import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;

@Service
public class NotificationService implements ModelNotificationService {
    private final NotificationRepository notificationRepo;
    private final TaskRepository taskRepo;
    private final ModelTaskService taskService;

    public NotificationService(NotificationRepository notificationRepo, TaskRepository taskRepo, ModelTaskService taskService) {
        this.notificationRepo = notificationRepo;
        this.taskRepo = taskRepo;
        this.taskService = taskService;
    }

    public Collection<Notification> getUserNotifications(Long userId) {
        return notificationRepo.findByUserId(userId);
    }

    public Collection<Notification> getAllNotifications() {
        return notificationRepo.findAll().stream()
                .filter(notification -> {
                    Task task = taskRepo.findAllAndDeletedFalse().stream()
                            .filter(t -> Objects.equals(t.getTaskId(), notification.getTaskId()))
                            .findFirst()
                            .orElse(null);

                    if (task == null) {
                        return false;
                    }

                    return task.getCreationDate().isBefore(task.getTargetDate());
                })
                .collect(Collectors.toList());
    }

    public void updateNotification(Long userId, Long taskId, String eventType) {
        if (!eventType.equals("created") && !eventType.equals("deleted")) return;

        Notification existing = notificationRepo.findByUserId(userId).stream()
                .filter(n -> Objects.equals(n.getTaskId(), taskId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setNotificationValue(eventType);
            notificationRepo.saveNotification(existing);
        } else {
            Notification newNotif = new Notification();
            newNotif.setNotificationId(taskId);
            newNotif.setUserId(userId);
            newNotif.setTaskId(taskId);
            newNotif.setNotificationValue(eventType);
            notificationRepo.saveNotification(newNotif);
        }
    }

    @Scheduled(fixedRate = 60000)
    @Async
    public void checkOverdueTasks() {
        Collection<Task> tasks = taskRepo.findAllAndDeletedFalse();

        tasks.stream()
                .filter(task -> task.getTargetDate().toInstant().isBefore(Instant.now()))
                .forEach(task -> {
                    taskService.deleteTask(task.getUserId(), task.getTaskId());
                });
    }

}
