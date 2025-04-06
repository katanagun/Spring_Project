package com.project.demo.services;

import com.project.demo.models.Notification;

import java.util.List;

public interface ModelNotificationService {
    public Notification getNotification(long id, String type);
    public void putNotification(Notification notification, String type);

}
