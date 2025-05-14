package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService implements ModelNotificationService {
    TaskService taskService;

    Map<Long, Notification> notifications = new HashMap<>();

    public Collection<Notification> getUserNotifications(long idUser){
        Collection<Notification> filteredNotification = new ArrayList<>();
        for (Notification notification: notifications.values()){
            if (notification.getIdUser() == idUser){
                filteredNotification.add(notification);
            }
        }
        return filteredNotification;
    }

    public Collection<Notification> getNotification(){
        Collection<Notification> pendingNotification = new ArrayList<>();
        for (Notification notification: notifications.values()){
            if (taskService.tasks.get(notification.getIdTask()).getCreationDate().isBefore(taskService.tasks.get(notification.getIdTask()).getTargetDate())){
                pendingNotification.add(notification);
            }
        }
        return pendingNotification;
    }

}