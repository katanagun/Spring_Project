package com.project.demo.db.repositories;

import com.project.demo.db.Notification;

import java.util.Collection;

public interface NotificationRepository {
    Collection<Notification> findByUserId(Long UserId);
    Collection<Notification> findAll();
    void saveNotification(Notification notification);
}
