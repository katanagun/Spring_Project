package com.project.demo.services;

import com.project.demo.db.Notification;

import java.util.List;

public interface ModelNotificationService {
    public Notification getNotification(long id, String type);
    public void putNotification(Notification notification, String type);

}
