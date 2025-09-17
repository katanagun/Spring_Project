package com.project.demo.services;

import com.project.demo.db.Notification;
import com.project.demo.db.Task;
import com.project.demo.db.repositories.NotificationRepository;
import com.project.demo.db.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NotificationService implements ModelNotificationService {
    private final NotificationRepository notificationRepo;
    private final TaskRepository taskRepo;

    public NotificationService(NotificationRepository notificationRepo, TaskRepository taskRepo) {
        this.notificationRepo = notificationRepo;
        this.taskRepo = taskRepo;
    }

    public Collection<Notification> getUserNotifications(Long userId){
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
            newNotif.setNotificationId(taskId); // ← присваиваем taskId как notificationId
            newNotif.setUserId(userId);
            newNotif.setTaskId(taskId);
            newNotif.setNotificationValue(eventType);
            notificationRepo.saveNotification(newNotif);
        }
    }

}
