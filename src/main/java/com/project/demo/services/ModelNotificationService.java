package com.project.demo.services;

import com.project.demo.db.Notification;

import java.util.Collection;

public interface ModelNotificationService {
    public Collection<Notification> getUserNotifications(Long userId);
    public Collection<Notification> getAllNotifications();
    void checkOverdueTasks();
    void updateNotification(Long userId, Long taskId, String eventType);
}