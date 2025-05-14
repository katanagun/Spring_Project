package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService implements ModelTaskService {
    private final UserService userService;
    private final NotificationService notificationService;

    public TaskService(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    Map<Long, Task> tasks = new HashMap<>();

    public void insertTask(long idTask, long idUser, String valueTask, ZonedDateTime targetDate){
        if (userService.users.get(idUser) != null){
            Task task = new Task(idTask, idUser, valueTask, targetDate);
            tasks.put(task.getIdTask(), task);
        }
        else{
            throw new IllegalArgumentException("User with id " + idUser + " not found.");
        }
    }

    public Collection<Task> getAllTasks() {
        return tasks.values().stream()
                .filter(task -> {
                    Notification notification = notificationService.notifications.get(task.getIdTask());

                    if (notification == null) {
                        return true;
                    }

                    return !"deleted".equals(notification.getValue());
                })
                .collect(Collectors.toList());
    }

    public Collection<Task> getTasks() {
        return tasks.values().stream()
                .filter(task -> {
                    Notification notification = notificationService.notifications.get(task.getIdTask());

                    if (notification == null) {
                        return task.getCreationDate().isBefore(task.getTargetDate());
                    }

                    return task.getCreationDate().isBefore(task.getTargetDate()) &&
                            !"deleted".equals(notification.getValue());
                })
                .collect(Collectors.toList());
    }

    public void deleteTask(long idUser, long idTask, String notification){
        notificationService.notifications.put(idTask, new Notification(idUser, idTask, "deleted"));
    }

}
