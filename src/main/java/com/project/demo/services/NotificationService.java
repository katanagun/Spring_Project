package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService implements ModelNotificationService {
    Map<String, Notification> notifications = new HashMap<>();

    public Notification getNotification(long idNotification, String type){
        return notifications.get(idNotification + ":" + type);

    }

    public void putNotification(Notification notification, String type){
        notifications.put(notification.getIdNotification() + ":" + type, notification);
    }

}
