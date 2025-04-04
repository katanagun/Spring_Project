package com.project.demo.services;

import com.project.demo.models.Notification;

import java.util.List;

public interface ModelNotificationService {
    public List<Notification> getNotification(long id, String purpose);
    public void putNotification(Notification notification);

}
