package com.project.demo.services;

import com.project.demo.models.Notification;
import com.project.demo.models.Task;
import com.project.demo.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService implements ModelNotificationService {
    List<Notification> notifications = new ArrayList<Notification>();

    public List<Notification> getNotification(long idNotification, String purpose){
        List<Notification> result = new ArrayList<Notification>();
        for (Notification element : notifications){
            if (element.getIdNotification() == idNotification && element.getPurpose().equalsIgnoreCase(purpose)){
                result.add(element);
            }
        }
        return result;
    }

    public void putNotification(Notification notification){
        notifications.add(notification);
    }

}
