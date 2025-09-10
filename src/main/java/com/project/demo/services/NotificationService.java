package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService implements ModelNotificationService {
    TaskService taskService;

    Map<Long, Notification> notifications = new HashMap<>();

    public Collection<Notification> getUserNotifications(long idUser) {
        return notifications.values().stream()
                .filter(notification -> notification.getIdUser() == idUser)
                .collect(Collectors.toList());
    }

    public Collection<Notification> getAllNotifications() {
        return notifications.values().stream()
                .filter(notification -> {
                    Task task = taskService.tasks.get(notification.getIdTask());

                    return task.getCreationDate().isBefore(task.getTargetDate());
                })
                .collect(Collectors.toList());
    }

}
