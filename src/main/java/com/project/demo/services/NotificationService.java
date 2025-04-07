package com.project.demo.services;

import com.project.demo.db.Notification;
import com.project.demo.db.repository.NotificationRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService implements ModelNotificationService {
    @Autowired
    private NotificationRep notificationRep;

    public Notification getNotification(long idNotification, String type){
        Optional<Notification> optionalNotification = notificationRep.findById(idNotification).filter(notification -> Objects.equals(notification.getType(), type));
        return optionalNotification.orElse(null);
    }

    public void putNotification(Notification notification, String type){
        notification.setType(type);
        notificationRep.save(notification);
    }

}
